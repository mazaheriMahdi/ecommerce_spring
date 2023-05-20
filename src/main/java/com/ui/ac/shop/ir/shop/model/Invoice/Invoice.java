package com.ui.ac.shop.ir.shop.model.Invoice;

import controller.CartItemController;
import controller.Exeptions.ObjectDoesNotExist;
import model.Cart.CartItem;

import java.util.ArrayList;

public class Invoice {
    private static int lastId;

    static {
        lastId = 0;
    }

    private final int id;
    private final String date;
    private final ArrayList<Integer> cartItems;
    private float amount;

    public Invoice(ArrayList<Integer> cartItems) throws ObjectDoesNotExist {
        lastId++;
        id = lastId;
        this.date = java.time.LocalDate.now().toString();
        this.cartItems = cartItems;
        this.amount = 0;
        for (Integer cartItem : cartItems) {
            CartItem currentItem = CartItemController.getById(cartItem);
            assert currentItem != null;
            this.amount += currentItem.getQuantity() * currentItem.getUnitPrice();
        }
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Integer> getCartItems() {
        return cartItems;
    }

    @Override
    public String toString() {
        StringBuilder items = new StringBuilder();
        float amount = 0;
        for (int itemId : cartItems) {
            try {
                CartItem cartItem = CartItemController.getById(itemId);
                items.append(cartItem);
                amount += cartItem.getUnitPrice() * cartItem.getQuantity();
            } catch (ObjectDoesNotExist e) {
                throw new RuntimeException(e);
            }
        }
        return "id=" + id + "\n" +
                "date='" + date + "\n" +
                "items=\n" + items + "\n" +
                "amount=" + amount + "\n"
                ;
    }
}
