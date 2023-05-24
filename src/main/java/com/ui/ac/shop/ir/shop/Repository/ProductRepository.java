package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.Category;
import com.ui.ac.shop.ir.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {
    Optional<Product> findProductById(Long id);
    Optional<Product> findProductByName(String name);
    Optional<Product> searchAllByName(String searchQuery);
    Optional<Product> findAllByCategory(Category category);


 }
