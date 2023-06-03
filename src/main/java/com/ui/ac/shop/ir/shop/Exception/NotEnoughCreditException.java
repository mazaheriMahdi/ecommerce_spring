package com.ui.ac.shop.ir.shop.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotEnoughCreditException extends RuntimeException {
    public NotEnoughCreditException() {
        super("Not Enough Credit");
    }
}
