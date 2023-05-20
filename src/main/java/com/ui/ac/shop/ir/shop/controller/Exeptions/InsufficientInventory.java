package com.ui.ac.shop.ir.shop.controller.Exeptions;

public class InsufficientInventory extends Exception {
    public InsufficientInventory() {
        super("Insufficient inventory.");
    }
}
