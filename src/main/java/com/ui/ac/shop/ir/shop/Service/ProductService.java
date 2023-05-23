package com.ui.ac.shop.ir.shop.Service;

import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.ProductPropertyRepository;
import com.ui.ac.shop.ir.shop.Repository.ProductRepository;
//import com.ui.ac.shop.ir.shop.model.Category;
import com.ui.ac.shop.ir.shop.Repository.PropertiesKeyRepository;
import com.ui.ac.shop.ir.shop.model.Product;
import com.ui.ac.shop.ir.shop.model.ProductProperty;
import com.ui.ac.shop.ir.shop.model.PropertiesKey;
import com.ui.ac.shop.ir.shop.model.RequestModels.AddProductRequest;
import com.ui.ac.shop.ir.shop.model.ResponseModels.FullProduct;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ProductPropertiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductPropertyRepository productPropertyRepository;
    private PropertiesKeyRepository propertiesKeyRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductPropertyRepository productPropertyRepository, PropertiesKeyRepository propertiesKeyRepository) {
        this.productRepository = productRepository;
        this.productPropertyRepository = productPropertyRepository;
        this.propertiesKeyRepository = propertiesKeyRepository;
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

    public void addProduct(AddProductRequest product){
        productRepository.save(product.getProduct());
        for (ProductPropertiesResponse p : product.getProductProperty()) {
            PropertiesKey propertiesKey ;
            if(!propertiesKeyRepository.existsByName(p.getName())){
                propertiesKeyRepository.save(new PropertiesKey(p.getName()));
            }
            propertiesKey = propertiesKeyRepository.findByName(p.getName()).get();
            productPropertyRepository.save(new ProductProperty(product.getProduct() , propertiesKey , p.getValue()));
        }
    }

    public void addSimpleProduct(Product product){
        productRepository.save(product);
    }
}
