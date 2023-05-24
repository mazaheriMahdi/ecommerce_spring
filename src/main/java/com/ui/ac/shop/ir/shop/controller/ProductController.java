package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.Exception.InvalidTokenException;
import com.ui.ac.shop.ir.shop.Exception.NoTokenProvidedException;
import com.ui.ac.shop.ir.shop.Service.ProductPropertyServices;
import com.ui.ac.shop.ir.shop.Service.ProductService;
import com.ui.ac.shop.ir.shop.model.Product;
import com.ui.ac.shop.ir.shop.model.ProductProperty;
import com.ui.ac.shop.ir.shop.model.RequestModels.AddProductRequest;
import com.ui.ac.shop.ir.shop.model.ResponseModels.FullProduct;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ProductPropertiesResponse;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        if(!UserCheckController.getIsLoggedIn())throw new InvalidTokenException();

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

    @GetMapping(value = "test")
    public ResponseEntity<AddProductRequest> getf(@RequestHeader(HttpHeaders.AUTHORIZATION) UUID token){
        AddProductRequest addProductRequest = new AddProductRequest(productService.getProducts().get(0) , List.of(new ProductPropertiesResponse("CAR" , "101")));
        System.out.println(token);
        return new ResponseEntity<>( addProductRequest , HttpStatus.OK);
    }



    @PostMapping
    public void addProduct(@RequestBody AddProductRequest product) {
        System.out.println(product);
        this.productService.addProduct(product);

    }
}
