package com.challenge.productservice.domain.review;

public class ProductReview {

    private String productId;
    private Float averageScore;
    private Long numberOfReview;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }

    public Long getNumberOfReview() {
        return numberOfReview;
    }

    public void setNumberOfReview(Long numberOfReview) {
        this.numberOfReview = numberOfReview;
    }

}
