package com.ui.ac.shop.ir.shop.controller.Exeptions;

public class CreditNotEnoughException extends Exception {
    public CreditNotEnoughException() {
        super("You don't have enough Credit.");
    }
}
