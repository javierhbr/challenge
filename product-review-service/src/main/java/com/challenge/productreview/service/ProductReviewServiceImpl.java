package com.challenge.productreview.service;

import com.challenge.productreview.dto.ProductReviewDTO;
import com.challenge.productreview.exception.ProductReviewNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    @Override
    public ProductReviewDTO getProductReviewById(String productId) throws ProductReviewNotFoundException {
        return null;
    }

    @Override
    public ProductReviewDTO createProductReview(ProductReviewDTO reviewDTO) {
        return null;
    }

    @Override
    public ProductReviewDTO updateProductReviewById(ProductReviewDTO reviewDTO) throws ProductReviewNotFoundException {
        return null;
    }

    @Override
    public ProductReviewDTO deleteProductReviewById(String productId) throws ProductReviewNotFoundException {
        return null;
    }
}
