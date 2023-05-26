package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.*;
import com.ui.ac.shop.ir.shop.Repository.CartItemRepository;
import com.ui.ac.shop.ir.shop.Repository.CartRepository;
import com.ui.ac.shop.ir.shop.Repository.OrderItemRepository;
import com.ui.ac.shop.ir.shop.Repository.OrderRepository;
import com.ui.ac.shop.ir.shop.model.Cart;
import com.ui.ac.shop.ir.shop.model.CartItem;
import com.ui.ac.shop.ir.shop.model.Order;
import com.ui.ac.shop.ir.shop.model.OrderItem;
import com.ui.ac.shop.ir.shop.model.ResponseModels.OrderItemResponseModel;
import com.ui.ac.shop.ir.shop.model.ResponseModels.OrderResponseModel;
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

    @Autowired
    public OrderService(OrderRepository orderRepository, CartItemRepository cartItemRepository, CartRepository cartRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public void createOrder(UUID cartId, Long customerId) {
        Optional<Cart> cart = cartRepository.findById(cartId);

        // Check if cart is present
        if (cart.isPresent()) {
            // Check if cart belongs to the customer
            if (!cart.get().getCustomer().getId().equals(customerId)) throw new InvalidCartIdException();

            Optional<List<CartItem>> cartItems = cartItemRepository.findCartItemsByCartId(cartId);

            // Check if cart items is present
            if (cartItems.isPresent()) {
                // Create order
                Order order = new Order(cart.get().getCustomer());
                orderRepository.save(order);

                // Create order items
                for (CartItem cartItem : cartItems.get()) {
                    OrderItem orderItem = new OrderItem(order, cartItem.getProduct(), cartItem.getQuantity());
                    orderItemRepository.save(orderItem);
                }
                // Delete cart items
                cartItemRepository.deleteAll(cartItems.get());
//                cartRepository.delete(cart.get());

                // If cart items is not present
            } else throw new CartIsEmptyException();

            // If cart is not present
        } else throw new EntityNotFoundException("Cart", cartId);

    }
    public OrderItemResponseModel getOrderItemResponse(Long id , Long customerId) {

        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        //check if order item is exist
        if (orderItem.isPresent()) {
            //check if order item belongs to the customer
            if (!orderItem.get().getOrder().getCustomer().getId().equals(customerId)) throw new EntityNotFoundException("Order Item", id);
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
        if (orders.isPresent()) {
            List<OrderResponseModel> orderResponseModels = new ArrayList<>();

            //get all order item for each order
            for (Order order : orders.get()) {
                Optional<List<OrderItem>> orderItems = orderItemRepository.findAllByOrderId(order.getId());
                //check if order item is exist for given order
                if (orderItems.isEmpty()) throw new NoOrderItemException();

                //add order response model to list
                orderResponseModels.add(new OrderResponseModel(
                        order.getId(),
                        order.getCustomer(),
                        order.getPlacedAt(),
                        orderItems.get()
                ));

            }
            return orderResponseModels;
        }
        throw new NoOrderForGivenCustomer();
    }

}
