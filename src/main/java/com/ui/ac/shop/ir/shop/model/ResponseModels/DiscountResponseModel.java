package com.ui.ac.shop.ir.shop.model.ResponseModels;

import lombok.Value;

@Value
public class DiscountResponseModel {
    Long id;
    String code;
    String userName;
    int percent;
    int count;
    int dayLeft;
}
