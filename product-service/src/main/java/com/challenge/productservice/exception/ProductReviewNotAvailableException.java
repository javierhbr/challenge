package com.challenge.productservice.exception;


public class ProductReviewNotAvailableException extends RuntimeException{

    public ProductReviewNotAvailableException(String message){
        super(message);
    }
}
