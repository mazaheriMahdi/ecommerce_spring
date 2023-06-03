package com.ui.ac.shop.ir.shop.model.ResponseModels;


import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;

@AllArgsConstructor
@ToString
@Value
public class ReviewResponseModel {


    String content;
    String userName;

}
