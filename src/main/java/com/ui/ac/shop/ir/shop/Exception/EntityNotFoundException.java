package com.ui.ac.shop.ir.shop.Exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entityName , Long id){
        super(entityName + " With id : " + id + " Not Found");
    }
    public EntityNotFoundException(String entityName ,String property ,String value){
        super(entityName + "With  "+ property +" : " + value + " Not Found");
    }
}
