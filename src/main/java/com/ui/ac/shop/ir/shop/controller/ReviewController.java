package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.Service.ProductService;
import com.ui.ac.shop.ir.shop.Service.ReviewService;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.ResponseModels.MessageResponseModel;
import com.ui.ac.shop.ir.shop.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/review")
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
    @GetMapping(value = "/add")
    public ResponseEntity<MessageResponseModel> addReview(
            @RequestHeader(HttpHeaders.AUTHORIZATION) UUID uuid,
            @RequestParam(name = "product_id") Long productId,
            @RequestParam(name = "content") String review) {
        reviewService.addReview(new Review(review , productService.getProductById(productId) , userService.getByToken(uuid)));
        return new  ResponseEntity<MessageResponseModel>(new MessageResponseModel("Review added"),HttpStatus.OK);
    }
}
