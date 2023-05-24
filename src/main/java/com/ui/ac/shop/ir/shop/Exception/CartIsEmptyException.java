package com.ui.ac.shop.ir.shop.Exception;

public class CartIsEmptyException extends RuntimeException{
    public CartIsEmptyException() {
        super("Cart is empty");
    }
}
