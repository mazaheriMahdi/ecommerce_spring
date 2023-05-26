package com.ui.ac.shop.ir.shop.model.RequestModels;


import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class CustomerPatchRequestModel {
    Long id;
    String firstName;

    String email;
}
