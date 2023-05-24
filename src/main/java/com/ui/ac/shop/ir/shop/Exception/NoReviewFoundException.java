package com.ui.ac.shop.ir.shop.Exception;

public class NoReviewFoundException extends EntityNotFoundException {

    public NoReviewFoundException(Long productId) {
        super("Review" , productId);
    }
}
