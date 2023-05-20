package com.ui.ac.shop.ir.shop.controller;

import controller.Exeptions.ObjectDoesNotExist;
import model.Cart.CartItem;
import model.Product.BaseProduct;

import java.util.ArrayList;

public class CartItemController {
    private static final ArrayList<CartItem> cartItems;

    static {
        cartItems = new ArrayList<>();
    }

    public static CartItem addCartItem(Object object, int quantity) throws ObjectDoesNotExist {
        cartItems.add(new CartItem(((BaseProduct) object).getId(), quantity));
        return cartItems.get(cartItems.toArray().length - 1);
    }

    public static ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public static CartItem getById(int id) throws ObjectDoesNotExist {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId() == id) {
                return cartItem;
            }
        }
        throw new ObjectDoesNotExist("point");
    }
}

