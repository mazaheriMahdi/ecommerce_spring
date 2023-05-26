package com.ui.ac.shop.ir.shop.model.ResponseModels;

import com.ui.ac.shop.ir.shop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class CartItemResponseModel {

    Product product;
    int quantity;
    double totalPrice;

    public CartItemResponseModel(Product product, int quantity, double totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}

