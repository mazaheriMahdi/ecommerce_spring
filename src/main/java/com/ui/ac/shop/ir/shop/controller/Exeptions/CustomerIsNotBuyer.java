package com.ui.ac.shop.ir.shop.controller.Exeptions;

public class CustomerIsNotBuyer extends Exception {
    public CustomerIsNotBuyer() {
        super("This customer has not purchased this product.");
    }
}


