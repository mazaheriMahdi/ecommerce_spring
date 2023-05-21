package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.Service.ProductService;
import com.ui.ac.shop.ir.shop.model.Product;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"","/{productId}"})
    public List<Product> getProduct(@PathVariable @Nullable Long productId) {
        if (productId!=null){
            try {

            return List.of(productService.getProductById(productId)) ;
            }catch (IllegalAccessException o){
                return List.of(null);
            }
        }
        return productService.getProducts();
    }

    @PostMapping
    public void addStudent(@RequestBody Product product){
        this.productService.addProduct(product);

    }
}
