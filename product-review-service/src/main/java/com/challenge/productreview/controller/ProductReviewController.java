package com.challenge.productreview.controller;

import com.challenge.productreview.request.ProductReviewRequest;
import com.challenge.productreview.response.ProductReviewResponse;
import com.challenge.productreview.exception.ProductReviewNotFoundException;
import com.challenge.productreview.service.ProductReviewService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("review")
public class ProductReviewController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ProductReviewService productReviewService;


    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @ApiOperation("Return product review for any valid productId.")
    @GetMapping("/{productId}")
    public ProductReviewResponse getProductReview(@Valid @PathVariable("productId") String productId) throws ProductReviewNotFoundException {
        logger.info("getting review for {}", productId);
        ProductReviewResponse response = new ProductReviewResponse();
        response.setProductReview(productReviewService.getProductReviewById(productId));
        return response;
    }

    @ApiOperation("Create a new product review.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReviewResponse postProductReview(@Valid @RequestBody ProductReviewRequest reviewRequest) throws ProductReviewNotFoundException {
        ProductReviewResponse response = new ProductReviewResponse();
        response.setProductReview(productReviewService.createProductReview(reviewRequest.getProductReview()));
        return response;
    }

    @ApiOperation("Update an existing product review.")
    @PutMapping
    public ProductReviewResponse putProductReview(@Valid @RequestBody ProductReviewRequest reviewRequest) throws ProductReviewNotFoundException {
        ProductReviewResponse response = new ProductReviewResponse();
        response.setProductReview(productReviewService.updateProductReviewById(reviewRequest.getProductReview()));
        return response;
    }

    @ApiOperation("Delete an existing product review.")
    @DeleteMapping("/{productId}")
    public void deleteProductReview(@Valid @PathVariable("productId") String productId) throws ProductReviewNotFoundException {
             productReviewService.deleteProductReviewById(productId);
    }

}
