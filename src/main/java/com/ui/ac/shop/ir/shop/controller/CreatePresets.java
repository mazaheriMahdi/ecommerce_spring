package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.Service.CategoryService;
import com.ui.ac.shop.ir.shop.Service.ProductPropertyServices;
import com.ui.ac.shop.ir.shop.Service.ProductService;
import com.ui.ac.shop.ir.shop.Service.PropertiesKeyService;
import com.ui.ac.shop.ir.shop.model.Product.Category;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.Product.ProductProperty;
import com.ui.ac.shop.ir.shop.model.Product.PropertiesKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/create")
public class CreatePresets {

    ProductService productService;
    ProductPropertyServices productPropertyServices;
    PropertiesKeyService propertiesKeyService;
    CategoryService categoryService;

    @Autowired
    public CreatePresets(ProductService productService, ProductPropertyServices productPropertyServices, PropertiesKeyService propertiesKeyService, CategoryService categoryService) {
        this.productService = productService;
        this.productPropertyServices = productPropertyServices;
        this.propertiesKeyService = propertiesKeyService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public Product create() {
        PropertiesKey propertiesKey = new PropertiesKey("Engine Copacity");
        propertiesKeyService.addPropertiesKey(propertiesKey);


        Category category = new Category("CAR");
        categoryService.addCategory(category);


        Product product = new Product("BMW X6", 100, 10000, 10, category);
        productService.addSimpleProduct(product);


        ProductProperty productProperty = new ProductProperty(product , propertiesKey , "1.1 Liter");
        productPropertyServices.addProductProperty(productProperty);

        return product;
    }
}
