package com.challenge.productreview.domain;

public class ProductReviewEntity {


    private String productId;
    private float averageScore;
    private long numberOfReview;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public long getNumberOfReview() {
        return numberOfReview;
    }

    public void setNumberOfReview(long numberOfReview) {
        this.numberOfReview = numberOfReview;
    }
}
