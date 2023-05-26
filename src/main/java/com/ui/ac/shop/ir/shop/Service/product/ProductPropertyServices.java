package com.ui.ac.shop.ir.shop.Service.product;

import com.ui.ac.shop.ir.shop.Repository.ProductPropertyRepository;
import com.ui.ac.shop.ir.shop.model.Product.ProductProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPropertyServices {
    ProductPropertyRepository productPropertyRepository;

    @Autowired
    public ProductPropertyServices(ProductPropertyRepository productRepository) {
        this.productPropertyRepository = productRepository;
    }

    public List<ProductProperty> getProductProperties(){
        return productPropertyRepository.findAll();
    }
    public void addProductProperty(ProductProperty  productProperty){
        productPropertyRepository.save(productProperty);
    }

    public List<ProductProperty> getByProductId(Long id){
        return productPropertyRepository.searchAllByProduct_Id(id);
    }
}
