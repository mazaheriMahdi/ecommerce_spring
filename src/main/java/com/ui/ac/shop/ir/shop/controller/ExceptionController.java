package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.InvalidTokenException;
import com.ui.ac.shop.ir.shop.Exception.NoTokenProvidedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({NoTokenProvidedException.class , InvalidTokenException.class})
    public ResponseEntity<Map<String , String>> handleNoTokenProvidedException(){
        Map<String , String> map = new HashMap<>();
        map.put("ERROR" , "Invalided Token");
        return new ResponseEntity<>( map , HttpStatus.FAILED_DEPENDENCY);

    }



}
