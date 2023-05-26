package com.ui.ac.shop.ir.shop.Exception;

public class NoOrderItemException extends RuntimeException{
    public NoOrderItemException() {
        super("No Order Item Found");
    }
}
