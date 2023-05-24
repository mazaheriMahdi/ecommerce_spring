package com.ui.ac.shop.ir.shop.model.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor
@Value
public class CartCreateResponseModel {
    UUID cartId;
}
