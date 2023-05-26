package com.ui.ac.shop.ir.shop.Repository;


import com.ui.ac.shop.ir.shop.model.Product.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPaginationRepository extends PagingAndSortingRepository<Product , Long> {
}
