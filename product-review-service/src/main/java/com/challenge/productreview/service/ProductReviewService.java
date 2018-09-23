package com.challenge.productreview.service;

import com.challenge.productreview.dto.ProductReviewDTO;
import com.challenge.productreview.exception.ProductReviewNotFoundException;

public interface ProductReviewService {

    ProductReviewDTO getProductReviewById(String productId) throws ProductReviewNotFoundException;

    ProductReviewDTO createProductReview(ProductReviewDTO reviewDTO);

    ProductReviewDTO updateProductReviewById(ProductReviewDTO reviewDTO) throws ProductReviewNotFoundException;

    void deleteProductReviewById(String productId) throws ProductReviewNotFoundException;

}
