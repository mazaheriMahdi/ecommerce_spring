package com.ui.ac.shop.ir.shop.Repository;


import com.ui.ac.shop.ir.shop.model.Product.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPaginationRepository extends PagingAndSortingRepository<Product , Long> {
    @JoinColumn(name = "category_name")
    Page<Product> findProductsByNameContainingOrCategoryName(String name , String categoryName , Pageable pageable);
}
