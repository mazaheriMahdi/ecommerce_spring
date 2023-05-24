package com.ui.ac.shop.ir.shop.Exception;

public class NoTokenProvidedException extends RuntimeException{
    public NoTokenProvidedException() {
        super("No token provided");
    }
}
