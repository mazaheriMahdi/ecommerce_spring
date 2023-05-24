package com.ui.ac.shop.ir.shop.Service;

import com.ui.ac.shop.ir.shop.Exception.NoReviewFoundException;
import com.ui.ac.shop.ir.shop.Repository.ReviewRepository;
import com.ui.ac.shop.ir.shop.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Review> getReviewByProductId(Long productId){
        Optional<Review> reviews =  reviewRepository.findAllByProductId(productId);
        if (reviews.isPresent())return reviews.stream().toList();
        throw new NoReviewFoundException(productId);
    }

}
