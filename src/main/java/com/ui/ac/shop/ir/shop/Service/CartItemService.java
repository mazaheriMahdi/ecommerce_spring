package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.CartIsEmptyException;
import com.ui.ac.shop.ir.shop.Repository.CartItemRepository;
import com.ui.ac.shop.ir.shop.model.Cart;
import com.ui.ac.shop.ir.shop.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemService {
    CartItemRepository cartItemRepository;


    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getCartItems(UUID cartId){
        Optional<CartItem> cartItem = cartItemRepository.findCartItemsByCartId(cartId);
        if (cartItem.isPresent()){
            return cartItem.stream().toList();
        }throw new CartIsEmptyException();
    }

    public void addCartItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }
}
