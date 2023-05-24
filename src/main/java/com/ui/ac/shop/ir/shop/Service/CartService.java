package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Repository.CartRepository;
import com.ui.ac.shop.ir.shop.Repository.CustomerRepository;
import com.ui.ac.shop.ir.shop.model.Cart;
import com.ui.ac.shop.ir.shop.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    CartRepository cartRepository;
    CustomerRepository customerRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    public void deleteCart(UUID id){
        cartRepository.deleteById(id);
    }

    public Cart getCustomerCart(Long id ){
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            Optional<Cart> cart = cartRepository.findCartByCustomerId(id);
            if (cart.isPresent()){
                return cart.get();
            }else {
                cartRepository.save(new Cart(customer.get()));
            }
        }
        return cartRepository.findCartByCustomerId(id).get();
    }


}
