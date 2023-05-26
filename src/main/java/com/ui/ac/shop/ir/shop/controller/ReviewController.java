package com.ui.ac.shop.ir.shop.controller;

import com.ui.ac.shop.ir.shop.Service.ApplicationService;
import com.ui.ac.shop.ir.shop.Service.product.ProductService;
import com.ui.ac.shop.ir.shop.Service.ReviewService;
import com.ui.ac.shop.ir.shop.Service.UserService;
import com.ui.ac.shop.ir.shop.model.Application;
import com.ui.ac.shop.ir.shop.model.ResponseModels.ReviewResponseModel;
import com.ui.ac.shop.ir.shop.model.Review;
import com.ui.ac.shop.ir.shop.model.Enums.Type;
import com.ui.ac.shop.ir.shop.model.User.User;
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
    ApplicationService applicationService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService, ProductService productService, ApplicationService applicationService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.productService = productService;
        this.applicationService = applicationService;
    }

    @GetMapping(value = "/{productId}/review")
    public ResponseEntity<List<ReviewResponseModel>> addReview(
            @PathVariable Long productId,
            @RequestAttribute("user") User user,
            @RequestParam(name = "content", defaultValue = "") String review
    ) {
        List<ReviewResponseModel> reviews;
        if (review.equals("")) {
            reviews = reviewService.getReviewResponseModelByProductId(productId);
        } else {
            Application application = new Application(Type.REVIEW);
            applicationService.addApplication(application);

            Review temp = new Review(review, productService.getProductById(productId), user, application);
            reviewService.addReview(temp);

            reviews = List.of(new ReviewResponseModel(temp.getComment(), temp.getUser().getName()));
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
