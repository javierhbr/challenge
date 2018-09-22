package com.challenge.productreview.response;

import com.challenge.productreview.dto.ProductReviewDTO;

public class ProductReviewResponse {

    private ProductReviewDTO productReview;

    public ProductReviewDTO getProductReview() {
        return productReview;
    }

    public void setProductReview(ProductReviewDTO productReview) {
        this.productReview = productReview;
    }
}
