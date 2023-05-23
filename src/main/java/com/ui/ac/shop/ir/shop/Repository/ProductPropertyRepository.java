package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.Product;
import com.ui.ac.shop.ir.shop.model.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPropertyRepository extends JpaRepository<ProductProperty, Long> {
    List<ProductProperty> searchAllByProduct_Id(Long productId);
}
