package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.Service.ProductPropertyServices;
import com.ui.ac.shop.ir.shop.Service.ProductService;
import com.ui.ac.shop.ir.shop.model.Product;
import com.ui.ac.shop.ir.shop.model.ProductProperty;
import com.ui.ac.shop.ir.shop.model.ResponseModels.FullProduct;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private ProductPropertyServices productPropertyServices;



    @Autowired
    public ProductController(ProductService productService, ProductPropertyServices productPropertyServices) {
        this.productService = productService;
        this.productPropertyServices = productPropertyServices;
    }
    private FullProduct toFullPRoduct(Product product){
        return new FullProduct(product , productPropertyServices.getByProductId(product.getId()));
    }

    @GetMapping(value = "")
    public ResponseEntity<List<FullProduct>> getProduct() {
        ArrayList<FullProduct> products = new ArrayList<>();
        for (Product product : productService.getProducts()) {
            products.add(toFullPRoduct(product));
        }
        return new ResponseEntity<>(products, HttpStatus.OK);

    }


    @GetMapping(value = "/{productID}")
    public ResponseEntity<FullProduct> getSingleProduct(@PathVariable Long productID){
        FullProduct product = new FullProduct(productService.getProductById(productID) , productPropertyServices.getByProductId(productID));
        return new ResponseEntity<>(product , HttpStatus.OK);

    }



    @PostMapping
    public void addStudent(@RequestBody Product product) {
        this.productService.addProduct(product);

    }
}
