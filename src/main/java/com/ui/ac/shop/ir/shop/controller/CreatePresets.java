package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.model.Category;
import com.ui.ac.shop.ir.shop.model.Product;
import com.ui.ac.shop.ir.shop.model.ProductProperty;
import com.ui.ac.shop.ir.shop.model.PropertiesKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/create")
public class CreatePresets {

    @GetMapping
    public void create(){
        PropertiesKey propertiesKey = new PropertiesKey("Engine Copacity");
        ProductProperty productProperty = new ProductProperty(propertiesKey , 1.1);
        Category category = new Category("CAR");
        ArrayList<ProductProperty>  productProperties = new ArrayList<>();
        productProperties.add(productProperty);
        Product product = new Product("BMW X6" , 100 , 10000 , 10 , productProperties , category );

    }
}
