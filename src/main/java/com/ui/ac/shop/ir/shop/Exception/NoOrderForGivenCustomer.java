package com.ui.ac.shop.ir.shop.Exception;

public class NoOrderForGivenCustomer extends RuntimeException{
    public NoOrderForGivenCustomer() {
        super("No order found for given customer");
    }
}
