package com.ui.ac.shop.ir.shop.controller;


import com.ui.ac.shop.ir.shop.Exception.*;
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
        return new ResponseEntity<>( map , HttpStatus.NON_AUTHORITATIVE_INFORMATION);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String , String>> handleEntityNotFoundException(EntityNotFoundException e){
        Map<String , String> map = new HashMap<>();
        map.put("ERROR" , e.getMessage());
        return new ResponseEntity<>( map , HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Map<String , String>> handleWrongPasswordException(){
        Map<String , String> map = new HashMap<>();
        map.put("ERROR" , "Wrong Password");
        return new ResponseEntity<>( map , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.web.HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleHttpMediaTypeNotSupportedException() {
        Map<String, String> map = new HashMap<>();
        map.put("ERROR", "Unsupported Media Type");
        return new ResponseEntity<>(map, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("ERROR", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LackOfInventory.class)
    public ResponseEntity<Map<String, String>> handleLackOfInventory() {
        Map<String, String> map = new HashMap<>();
        map.put("ERROR", "Lack Of Inventory");
        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotEnoughCreditException.class)
    public ResponseEntity<Map<String, String>> handleNotEnoughCreditException() {
        Map<String, String> map = new HashMap<>();
        map.put("ERROR", "Not Enough Credit");
        return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String , String>> handleAccessDeniedException(){
        return new ResponseEntity<>(Map.of("ERROR" , "Access Denied") , HttpStatus.FORBIDDEN);
    }


}
