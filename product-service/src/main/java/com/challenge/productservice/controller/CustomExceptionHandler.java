package com.challenge.productservice.controller;

import com.challenge.productservice.exception.ErrorMsg;
import com.challenge.productservice.exception.ProductNotFoundException;
import com.challenge.productservice.exception.ProductResourceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> handlerProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        logger.error("handlerProductNotFoundException Got error: Exception:" + ex.getMessage());
        ErrorMsg  errorMsg = new ErrorMsg(ex.getMessage());
        return handleExceptionInternal(ex, errorMsg,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ProductResourceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<Object> handleProductResourceException(ProductResourceException ex, WebRequest request) {
        logger.error("handleProductResourceException Got error: Exception:" + ex.getMessage());
        ErrorMsg  errorMsg = new ErrorMsg(ex.getMessage());
        return handleExceptionInternal(ex, errorMsg,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
