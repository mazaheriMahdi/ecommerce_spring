package com.ui.ac.shop.ir.shop.Repository;

import com.ui.ac.shop.ir.shop.model.Product.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPropertyRepository extends JpaRepository<ProductProperty, Long> {
    List<ProductProperty> searchAllByProduct_Id(Long productId);
}
