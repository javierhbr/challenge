package com.challenge.productreview.request;

import com.challenge.productreview.dto.ProductReviewDTO;

public class ProductReviewRequest {

    private ProductReviewDTO productReview;

    public ProductReviewDTO getProductReview() {
        return productReview;
    }

    public void setProductReview(ProductReviewDTO productReview) {
        this.productReview = productReview;
    }
}
