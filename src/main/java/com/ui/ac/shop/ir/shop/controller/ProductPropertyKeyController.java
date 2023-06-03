package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.Service.ProductPropertyKeyService;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ProductPropertiesResponse;
import com.ui.ac.shop.ir.shop.model.ResponseModels.PropertyKeyResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/property")
public class ProductPropertyKeyController {
    ProductPropertyKeyService productPropertyKeyService;

    @Autowired
    public ProductPropertyKeyController(ProductPropertyKeyService productPropertyKeyService) {
        this.productPropertyKeyService = productPropertyKeyService;
    }

    @GetMapping
    public ResponseEntity<List<PropertyKeyResponseModel>> getAll(){
        return new ResponseEntity<>(productPropertyKeyService.getAllPropertyKey() , HttpStatus.OK);
    }
}
