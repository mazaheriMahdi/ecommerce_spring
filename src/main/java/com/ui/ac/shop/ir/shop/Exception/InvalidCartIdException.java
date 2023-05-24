package com.ui.ac.shop.ir.shop.Exception;

public class InvalidCartIdException extends RuntimeException{
    public InvalidCartIdException() {
        super("Please provide a valid cart id");
    }
}
