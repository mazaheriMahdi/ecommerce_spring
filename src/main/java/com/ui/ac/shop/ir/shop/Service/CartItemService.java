package com.ui.ac.shop.ir.shop.Service;


import com.ui.ac.shop.ir.shop.Exception.CartIsEmptyException;
import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.CartItemRepository;
import com.ui.ac.shop.ir.shop.model.Cart.CartItem;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.ResponseModels.CartItemResponseModel;
import jakarta.persistence.Lob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<CartItemResponseModel> getCartItemsResponseModel(UUID cartId) {
        Optional<List<CartItem>> cartItem = getCartItemList(cartId);
        if (cartItem.isPresent()) {
            List<CartItemResponseModel> cartItemResponseModels = new ArrayList<>();
            for (CartItem ct : cartItem.get()) {
                cartItemResponseModels.add(new CartItemResponseModel(ct.getProduct(), ct.getQuantity(), ct.getTotalPrice()));
            }
            return cartItemResponseModels;
        }
        throw new CartIsEmptyException();
    }

    public Optional<List<CartItem>> getCartItemList(UUID cartId) {
        Optional<List<CartItem>> cartItem = cartItemRepository.findCartItemsByCartId(cartId);
        return cartItem;
    }


    public void addCartItem(CartItem cartItem) {
        Optional<CartItem> cartItem1 = cartItemRepository.findCartItemByProductIdAndCart_Id(cartItem.getProduct().getId(), cartItem.getCart().getId());
        if (cartItem1.isPresent()) {
            cartItem1.get().setQuantity(cartItem1.get().getQuantity() + cartItem.getQuantity());
            cartItemRepository.save(cartItem1.get());
        } else {

            cartItemRepository.save(cartItem);
        }
    }

    public void deleteCartItem(UUID cartId , Long productId) {
        Optional<CartItem> cartItem = cartItemRepository.findCartItemByProductIdAndCart_Id(productId, cartId);
        if (cartItem.isPresent()) {
            cartItemRepository.delete(cartItem.get());
        }else throw new EntityNotFoundException("Cart Item" , "Product_id" , productId.toString());
    }
}
