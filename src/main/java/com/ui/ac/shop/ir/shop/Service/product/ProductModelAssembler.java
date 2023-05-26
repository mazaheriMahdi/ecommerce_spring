package com.ui.ac.shop.ir.shop.Service.product;


import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.ProductPropertyRepository;
import com.ui.ac.shop.ir.shop.controller.ProductController;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.Product.ProductProperty;
import com.ui.ac.shop.ir.shop.model.ResponseModels.FullProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, FullProduct> {

    private ProductPropertyRepository productPropertyRepository;


    @Autowired
    public ProductModelAssembler(ProductPropertyRepository productPropertyRepository) {
        super(ProductController.class, FullProduct.class);
        this.productPropertyRepository = productPropertyRepository;
    }

    @Override
    public FullProduct toModel(Product entity) {
        List<ProductProperty> productProperty = productPropertyRepository.searchAllByProduct_Id(entity.getId());
        if (productProperty.isEmpty())productProperty = new ArrayList<ProductProperty>();
        return new FullProduct(entity , productProperty );
    }
}
