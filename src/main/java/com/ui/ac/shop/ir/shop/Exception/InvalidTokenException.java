package com.ui.ac.shop.ir.shop.Exception;


public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("There is no user with given token");
    }
}
