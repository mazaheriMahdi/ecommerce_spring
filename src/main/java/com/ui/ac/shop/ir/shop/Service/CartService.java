package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.DiscountIsNotAvailableException;
import com.ui.ac.shop.ir.shop.Repository.CartItemRepository;
import com.ui.ac.shop.ir.shop.Repository.CartRepository;
import com.ui.ac.shop.ir.shop.Repository.CustomerRepository;
import com.ui.ac.shop.ir.shop.Repository.DiscountRepository;
import com.ui.ac.shop.ir.shop.model.Cart.Cart;
import com.ui.ac.shop.ir.shop.model.Cart.CartItem;
import com.ui.ac.shop.ir.shop.model.Discount;
import com.ui.ac.shop.ir.shop.model.User.Customer;
import com.ui.ac.shop.ir.shop.model.User.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    CartRepository cartRepository;
    CustomerRepository customerRepository;

    CartItemRepository cartItemRepository;
    DiscountRepository discountRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerRepository customerRepository, CartItemRepository cartItemRepository, DiscountRepository discountRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
        this.discountRepository = discountRepository;
    }

    public int getCartItemCount(Cart cart) {
        return cartItemRepository.countCartItemByCart(cart);
    }

    public void deleteCart(UUID id) {
        cartRepository.deleteById(id);
    }

    public Cart getCustomerCart(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Optional<Cart> cart = cartRepository.findCartByCustomerId(id);
            if (cart.isPresent()) {
                return cart.get();
            } else {
                cartRepository.save(new Cart(customer.get()));
            }
        }
        return cartRepository.findCartByCustomerId(id).get();
    }


    @Transactional
    public void setDiscountCode(Cart cart, User user, List<CartItem> cartItemList, String discountCode) {
        Optional<List<Discount>> discountOp = discountRepository.findAllByUserId(user.getId());
        if (discountOp.isPresent()) {
            for (Discount d : discountOp.get()) {
                if (d.getCode().equals(discountCode)) {
                    if (d.getCount() == 0) throw new DiscountIsNotAvailableException();
                    if (d.isExpired()) throw new DiscountIsNotAvailableException();
                    d.setCount(d.getCount() - 1);
                    discountRepository.save(d);
                    cart.setDiscount(d);
                    cartItemList.forEach(cartItem -> cartItem.setDiscount(d));
                    cartRepository.save(cart);
                    cartItemRepository.saveAll(cartItemList);
                    break;

                }
            }
        }


    }
}
