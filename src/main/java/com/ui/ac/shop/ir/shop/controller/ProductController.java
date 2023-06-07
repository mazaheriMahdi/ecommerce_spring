package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.Service.product.ProductModelAssembler;
import com.ui.ac.shop.ir.shop.Service.product.ProductPropertyServices;
import com.ui.ac.shop.ir.shop.Service.product.ProductService;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.RequestModels.AddProductRequest;
import com.ui.ac.shop.ir.shop.model.ResponseModels.FullProduct;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ProductPropertiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private ProductPropertyServices productPropertyServices;
    private PagedResourcesAssembler<Product> pagedResourcesAssembler;
    private ProductModelAssembler ProductModelAssembler;

    @Autowired
    public ProductController(ProductService productService, ProductPropertyServices productPropertyServices, PagedResourcesAssembler<Product> pagedResourcesAssembler, com.ui.ac.shop.ir.shop.Service.product.ProductModelAssembler productModelAssembler) {
        this.productService = productService;
        this.productPropertyServices = productPropertyServices;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        ProductModelAssembler = productModelAssembler;
    }


    @GetMapping(value = "")
    public ResponseEntity<PagedModel<FullProduct>> getProduct(Pageable pageable, @RequestParam(required = false , name = "search") String name) {
        Page<Product> products;
        if (name != null) {
            products = productService.searchProduct(name, pageable);
        } else products = productService.getPagedProduct(pageable);

        PagedModel<FullProduct> pagedModel = pagedResourcesAssembler
                .toModel(products, ProductModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);

    }


    @GetMapping(value = "/{productID}")
    public ResponseEntity<FullProduct> getSingleProduct(@PathVariable Long productID) {
        FullProduct product = new FullProduct(productService.getProductById(productID), productPropertyServices.getByProductId(productID));
        return new ResponseEntity<>(product, HttpStatus.OK);

    }



    @PostMapping
    public void addProduct(@RequestBody AddProductRequest product) {
        System.out.println(product);
        this.productService.addProduct(product);

    }
}
