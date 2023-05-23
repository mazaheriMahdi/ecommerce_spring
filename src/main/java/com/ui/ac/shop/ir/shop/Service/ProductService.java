package com.ui.ac.shop.ir.shop.Service;

import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.ProductPropertyRepository;
import com.ui.ac.shop.ir.shop.Repository.ProductRepository;
//import com.ui.ac.shop.ir.shop.model.Category;
import com.ui.ac.shop.ir.shop.model.Product;
import com.ui.ac.shop.ir.shop.model.ProductProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductPropertyRepository productPropertyRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductPropertyRepository productPropertyRepository) {
        this.productRepository = productRepository;
        this.productPropertyRepository = productPropertyRepository;
    }

    public List<Product> getProducts() {
        return  productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findProductById(id);
        if (product.isPresent()){
            return product.get();
        }
        else {
            throw new EntityNotFoundException("product" , id);
        }
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }
}
