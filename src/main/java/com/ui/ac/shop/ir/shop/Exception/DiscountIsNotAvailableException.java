package com.ui.ac.shop.ir.shop.Exception;

public class DiscountIsNotAvailableException extends RuntimeException {
    public DiscountIsNotAvailableException() {
        super("Discount is not available");
    }
}
