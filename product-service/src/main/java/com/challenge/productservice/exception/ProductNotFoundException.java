package com.challenge.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find any product")
public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message)
    {
        super(message);
    }
}
