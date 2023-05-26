package com.ui.ac.shop.ir.shop.model.ResponseModels;


import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Value
public class CartResponseModel {
    LocalDate placed_at;
    List<CartItemResponseModel> cartItems;

}
