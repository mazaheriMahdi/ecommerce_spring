package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.*;
import com.ui.ac.shop.ir.shop.Repository.*;
import com.ui.ac.shop.ir.shop.model.Cart.Cart;
import com.ui.ac.shop.ir.shop.model.Cart.CartItem;
import com.ui.ac.shop.ir.shop.model.Enums.OrderStatus;
import com.ui.ac.shop.ir.shop.model.Order.Order;
import com.ui.ac.shop.ir.shop.model.Order.OrderItem;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.ResponseModels.OrderItemResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.OrderResponseModel;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    OrderRepository orderRepository;
    CartItemRepository cartItemRepository;
    CartRepository cartRepository;
    OrderItemRepository orderItemRepository;
    CustomerRepository customerRepository;
    ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartItemRepository cartItemRepository, CartRepository cartRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }


    @Transactional
    public void createOrder(UUID cartId, Customer customer) {
        Optional<Cart> cart = cartRepository.findById(cartId);

        // Check if cart is present
        if (cart.isPresent()) {
            // Check if cart belongs to the customer
            if (!cart.get().getCustomer().getId().equals(customer.getId())) throw new InvalidCartIdException();

            Optional<List<CartItem>> cartItems = cartItemRepository.findCartItemsByCartId(cartId);

            // Check if cart items is present
            if (cartItems.isPresent()) {
                // Create order
                Order order = new Order(cart.get().getCustomer());
                order.setOrderStatus(OrderStatus.PENDING);

                double totalPrice = 0;
                // Create order items
                for (CartItem cartItem : cartItems.get()) {
                    Product product = cartItem.getProduct();
                    if (product.getCount() < cartItem.getQuantity()) throw new LackOfInventory();
                    product.setCount(product.getCount() - cartItem.getQuantity());
                    productRepository.save(product);

                    OrderItem orderItem = new OrderItem(order, cartItem.getProduct(), cartItem.getQuantity(), cartItem.getTotalPrice());
                    orderItemRepository.save(orderItem);
                    totalPrice += orderItem.getTotalPrice();
                }
                // Delete cart items
                cartItemRepository.deleteAll(cartItems.get());
//                cartRepository.delete(cart.get());
                if (totalPrice > customer.getCredit()) {
                    order.setOrderStatus(OrderStatus.FAILED);
                    orderRepository.save(order);
                    throw new NotEnoughCreditException();
                }
                ;
                customer.setCredit(customer.getCredit() - totalPrice);
                customerRepository.save(customer);

                order.setOrderStatus(OrderStatus.SUCCESS);
                orderRepository.save(order);
                // If cart items is not present
            } else throw new CartIsEmptyException();

            // If cart is not present
        } else throw new EntityNotFoundException("Cart", cartId);

    }

    public OrderItemResponseModel getOrderItemResponse(Long id, Long customerId) {

        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        //check if order item is exist
        if (orderItem.isPresent()) {
            //check if order item belongs to the customer
            if (!orderItem.get().getOrder().getCustomer().getId().equals(customerId))
                throw new EntityNotFoundException("Order Item", id);
            return new OrderItemResponseModel(
                    orderItem.get().getProduct().getId(),
                    orderItem.get().getQuantity(),
                    orderItem.get().getTotalPrice()
            );
        }
        throw new EntityNotFoundException("Order Item", id);
    }

    public List<Order> getAllCustomerOrder(Long id) {
        Optional<List<Order>> orders = orderRepository.findAllByCustomerId(id);
        if (orders.isPresent()) {
            return orders.get();
        }
        throw new NoOrderForGivenCustomer();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<OrderResponseModel> getAllCustomerOrderResponseModel(Long id) {
        Optional<List<Order>> orders = orderRepository.findAllByCustomerId(id);
        //check if order is exist for given customer
        return convertToOrderResponseModel(orders);
    }

    public List<OrderResponseModel> getAllAcceptedOrders(Customer customer) {
        Optional<List<Order>> orders = orderRepository.findAllByOrderStatus(OrderStatus.SUCCESS);
        //check if order is exist for given customer
        return convertToOrderResponseModel(orders);

    }

    private List<OrderResponseModel> convertToOrderResponseModel(Optional<List<Order>> orders) {
        if (orders.isPresent()) {
            List<OrderResponseModel> orderResponseModels = new ArrayList<>();

            //get all order item for each order
            for (Order order : orders.get()) {
                double totalPrice = 0;
                Optional<List<OrderItem>> orderItems = orderItemRepository.findAllByOrderId(order.getId());
                List<OrderItemResponseModel> orderItemResponseModels = new ArrayList<>();
                //check if order item is exist for given order
                if (orderItems.isEmpty()) throw new NoOrderItemException();
                for (OrderItem orderItem : orderItems.get()) {
                    orderItemResponseModels.add(new OrderItemResponseModel(
                            orderItem.getProduct().getId(),
                            orderItem.getQuantity(),
                            orderItem.getTotalPrice()
                    ));
                    totalPrice += orderItem.getTotalPrice();
                }


                //add order response model to list
                orderResponseModels.add(new OrderResponseModel(
                        order.getId(),
                        order.getCustomer().getId(),
                        order.getPlacedAt(),
                        orderItemResponseModels,
                        totalPrice
                ));

            }
            return orderResponseModels;
        }
        throw new NoOrderForGivenCustomer();
    }
}
