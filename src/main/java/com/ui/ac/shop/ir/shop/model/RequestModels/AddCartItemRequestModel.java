package com.ui.ac.shop.ir.shop.model.RequestModels;


import com.ui.ac.shop.ir.shop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Value;


@AllArgsConstructor
@Value
public class AddCartItemRequestModel {
    Long productId;
    int quantity;
}
