package com.ui.ac.shop.ir.shop.model.RequestModels;

import com.ui.ac.shop.ir.shop.model.Product;
import com.ui.ac.shop.ir.shop.model.ProductProperty;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ProductPropertiesResponse;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;


@Value
@AllArgsConstructor
public class AddProductRequest {
    Product product;
    List<ProductPropertiesResponse> productProperty;

}
