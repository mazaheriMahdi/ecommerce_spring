package com.ui.ac.shop.ir.shop.model.ResponseModels;


import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class ReviewResponseModel {

    Long product_id;
    String content;
    String userName;

}
