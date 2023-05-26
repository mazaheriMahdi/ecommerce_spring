package com.ui.ac.shop.ir.shop.Exception;

public class NotEnoughCreditException extends RuntimeException {
    public NotEnoughCreditException() {
        super("Not Enough Credit");
    }
}
