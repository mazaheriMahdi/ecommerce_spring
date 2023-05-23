package com.ui.ac.shop.ir.shop.Exception;

public class TakenEmailException extends RuntimeException {
    public TakenEmailException(String email) {
        super("This email address (" + email + ") is currently being used in another account");
    }
}
