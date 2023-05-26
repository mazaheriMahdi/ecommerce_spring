package com.ui.ac.shop.ir.shop.model.ResponseModels;


import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class GetProfileResponseModel {
    String name;
    String email;
    double credit;
    String avatarUrl;

}
