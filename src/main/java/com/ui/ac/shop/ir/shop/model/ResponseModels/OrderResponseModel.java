package com.ui.ac.shop.ir.shop.model.ResponseModels;


import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@Value
public class OrderResponseModel {
     Long id;
     Long customerId;
     LocalDate placedAt ;

     List<OrderItemResponseModel> orderItems;


}
