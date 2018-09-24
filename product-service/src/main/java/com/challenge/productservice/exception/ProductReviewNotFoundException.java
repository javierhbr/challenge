package com.challenge.productservice.exception;


public class ProductReviewNotFoundException extends RuntimeException{

    public ProductReviewNotFoundException(String message){
        super(message);
    }
}
