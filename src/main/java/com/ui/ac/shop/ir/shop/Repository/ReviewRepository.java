package com.ui.ac.shop.ir.shop.Repository;


import com.ui.ac.shop.ir.shop.model.Application;
import com.ui.ac.shop.ir.shop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long id);
    Optional<List<Review>> findReviewsByProductId(Long id);
    Optional<Review> findReviewsByApplication(Application application);

}
