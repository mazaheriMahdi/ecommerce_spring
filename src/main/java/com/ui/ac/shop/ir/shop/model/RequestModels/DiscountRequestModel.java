package com.ui.ac.shop.ir.shop.model.RequestModels;


import lombok.Value;

@Value
public class DiscountRequestModel {
    int percent;
    int count;
    int day;
    String email;
}
