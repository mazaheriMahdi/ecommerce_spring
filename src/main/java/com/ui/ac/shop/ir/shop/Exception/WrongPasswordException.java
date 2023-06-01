package com.ui.ac.shop.ir.shop.Exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Wrong password");
    }
}
