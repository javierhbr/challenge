package com.challenge.productservice.controller;


import com.challenge.productservice.exception.ProductNotFoundException;
import com.challenge.productservice.response.ProductResponse;
import com.challenge.productservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @GetMapping("/{productId}")
    public ProductResponse getProduct(@Valid @PathVariable("productId") String productId) throws ProductNotFoundException {
        return productService.getProductById(productId);
    }

}
