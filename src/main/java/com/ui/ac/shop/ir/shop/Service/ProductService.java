package com.ui.ac.shop.ir.shop.Service;

import com.ui.ac.shop.ir.shop.Repository.ProductRepository;
//import com.ui.ac.shop.ir.shop.model.Category;
import com.ui.ac.shop.ir.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) throws IllegalAccessException {
        Optional<Product> product = productRepository.findProductById(id);
        if (product.isPresent()){
            return product.get();
        }
        else {
            throw new IllegalAccessException();
        }
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }
}
