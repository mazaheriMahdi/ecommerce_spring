package com.ui.ac.shop.ir.shop.model.RequestModels;


import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class SignInRequestModel {
    String name;
    String email;
    String password;
    String avatar_url;
}


