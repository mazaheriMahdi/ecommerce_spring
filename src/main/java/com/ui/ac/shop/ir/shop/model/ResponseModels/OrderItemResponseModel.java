package com.ui.ac.shop.ir.shop.model.ResponseModels;


import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class OrderItemResponseModel {

    Long productId;
    int quantity;
    double totalPrice;

}
