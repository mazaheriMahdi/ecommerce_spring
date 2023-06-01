package com.ui.ac.shop.ir.shop.Service.product;

import com.ui.ac.shop.ir.shop.Exception.EntityNotFoundException;
import com.ui.ac.shop.ir.shop.Repository.ProductPaginationRepository;
import com.ui.ac.shop.ir.shop.Repository.ProductPropertyRepository;
import com.ui.ac.shop.ir.shop.Repository.ProductRepository;
//import com.ui.ac.shop.ir.shop.model.Product.Category;
import com.ui.ac.shop.ir.shop.Repository.PropertiesKeyRepository;
import com.ui.ac.shop.ir.shop.model.Product.Product;
import com.ui.ac.shop.ir.shop.model.Product.ProductProperty;
import com.ui.ac.shop.ir.shop.model.Product.PropertiesKey;
import com.ui.ac.shop.ir.shop.model.RequestModels.AddProductRequest;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ProductPropertiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private ProductRepository productRepository;

    private ProductPropertyRepository productPropertyRepository;

    private PropertiesKeyRepository propertiesKeyRepository;
    private ProductPaginationRepository productPaginationRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductPropertyRepository productPropertyRepository, PropertiesKeyRepository propertiesKeyRepository, ProductPaginationRepository productPaginationRepository) {
        this.productRepository = productRepository;
        this.productPropertyRepository = productPropertyRepository;
        this.propertiesKeyRepository = propertiesKeyRepository;
        this.productPaginationRepository = productPaginationRepository;
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

    public Page<Product> getPagedProduct(Pageable pageable){
        return productPaginationRepository.findAll(pageable);

    }

    public Page<Product> searchProduct(String name , Pageable pageable){
        return productPaginationRepository.findProductsByNameContainingOrCategoryName(name , name , pageable);
    }

    public void addSimpleProduct(Product product){
        productRepository.save(product);
    }
}
