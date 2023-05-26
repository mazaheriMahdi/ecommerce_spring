package com.ui.ac.shop.ir.shop.model.RequestModels;


import lombok.Value;

import java.util.UUID;

@Value
public class CreateOrderRequestModel {
    UUID cardId;

    public CreateOrderRequestModel(UUID cardId) {
        this.cardId = cardId;
    }
}
