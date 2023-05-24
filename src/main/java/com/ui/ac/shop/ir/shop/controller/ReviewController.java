package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.Service.ProductService;
import com.ui.ac.shop.ir.shop.Service.ReviewService;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ReviewController {
    ReviewService reviewService;
    UserService userService;
    ProductService productService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService, ProductService productService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping(value = "/{productId}/review")
    public ResponseEntity<List<Review>> addReview(
            @RequestParam(name = "content", defaultValue = "") String review,
            @PathVariable Long productId) {
        UserCheckController.CHECK_USER_LOGIN();
        List<Review> reviews;
        if (review.equals("")) {
            reviews = reviewService.getReviewByProductId(productId);
        } else {
            Review temp = new Review(review, productService.getProductById(productId), userService.getCurrentUser());
            reviewService.addReview(temp);
            reviews = List.of(temp);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
