package com.ui.ac.shop.ir.shop.controller.Exeptions;

public class WrongPassWordException extends Exception {
    public WrongPassWordException() {
        super("Wrong password!");
    }
}
