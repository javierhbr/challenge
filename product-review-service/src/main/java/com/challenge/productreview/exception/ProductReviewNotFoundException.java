package com.challenge.productreview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find any product review")
public class ProductReviewNotFoundException extends RuntimeException {

    public ProductReviewNotFoundException(String message){
        super(message);
    }
}
