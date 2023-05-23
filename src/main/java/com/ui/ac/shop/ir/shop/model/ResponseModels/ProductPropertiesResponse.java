package com.ui.ac.shop.ir.shop.model.ResponseModels;


import lombok.Value;

@Value
public class ProductPropertiesResponse {
    String name;
    String value;

    public ProductPropertiesResponse( String name, String value) {
        this.name = name;
        this.value = value;
    }
}
