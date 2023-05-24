package com.ui.ac.shop.ir.shop.Repository;


import com.ui.ac.shop.ir.shop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long id);
    Optional<Review> findAllByProductId(Long id);


}
