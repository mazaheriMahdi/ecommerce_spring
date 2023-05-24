package com.ui.ac.shop.ir.shop.Service;

import com.ui.ac.shop.ir.shop.Repository.ReviewRepository;
import com.ui.ac.shop.ir.shop.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public void addReview(Review review) {
        reviewRepository.save(review);
    }
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }


}
