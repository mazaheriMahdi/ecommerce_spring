package com.ui.ac.shop.ir.shop.model.RequestModels;


import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class LoginRequestModel {
    String email;
    String password;
}
