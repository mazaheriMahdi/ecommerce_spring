package com.ui.ac.shop.ir.shop.Exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entityName , Long id){
        super(entityName + "With id : " + id + " Not Found");
    }
}
