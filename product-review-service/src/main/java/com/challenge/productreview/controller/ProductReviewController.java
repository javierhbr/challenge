package com.challenge.productreview.controller;

import com.challenge.productreview.mapper.ProductReviewMapper;
import com.challenge.productreview.request.ProductReviewRequest;
import com.challenge.productreview.response.ProductReviewResponse;
import com.challenge.productreview.exception.ProductReviewNotFoundException;
import com.challenge.productreview.service.ProductReviewService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("review")
public class ProductReviewController {

    private ProductReviewService productReviewService;

    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @GetMapping("/{productId}")
    public ProductReviewResponse getProductReview(@Valid @PathVariable("productId") String productId) throws ProductReviewNotFoundException {
        ProductReviewResponse response = new ProductReviewResponse();
        response.setProductReview(productReviewService.getProductReviewById(productId));
        return response;
    }

    @ResponseBody
    @PostMapping
    public ProductReviewResponse postProductReview(@Valid @RequestBody ProductReviewRequest reviewRequest) throws ProductReviewNotFoundException {
        ProductReviewResponse response = new ProductReviewResponse();
        response.setProductReview(productReviewService.createProductReview(reviewRequest.getProductReview()));
        return response;
    }

    @ResponseBody
    @PutMapping
    public ProductReviewResponse putProductReview(@Valid @RequestBody ProductReviewRequest reviewRequest) throws ProductReviewNotFoundException {
        ProductReviewResponse response = new ProductReviewResponse();
        response.setProductReview(productReviewService.updateProductReviewById(reviewRequest.getProductReview()));
        return response;
    }

    @ResponseBody
    @DeleteMapping("/{productId}")
    public ProductReviewResponse deleteProductReview(@Valid @PathVariable("productId") String productId) throws ProductReviewNotFoundException {
        ProductReviewResponse response = new ProductReviewResponse();
        response.setProductReview(productReviewService.deleteProductReviewById(productId));
        return response;
    }

}
