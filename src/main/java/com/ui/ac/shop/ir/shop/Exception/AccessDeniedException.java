package com.ui.ac.shop.ir.shop.Exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException() {
        super("You don't have access to this end point");
    }
}
