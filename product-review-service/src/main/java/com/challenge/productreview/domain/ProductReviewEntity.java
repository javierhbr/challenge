package com.challenge.productreview.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "product_review",
        uniqueConstraints = @UniqueConstraint(name = "uc_product_id", columnNames = {"product_id"})
)
public class ProductReviewEntity {

    @Id
    @Column(nullable = false, name = "product_id")
    private String productId;

    @Column(nullable = false, name = "average_review_Score")
    @NotNull(message = "Average score can not be null.")
    private Float averageReviewScore;

    @Column(nullable = false, name = "number_of_reviews")
    @NotNull(message = "number of review can not be null.")
    private Long numberOfReview;

    public ProductReviewEntity() {
    }

    public ProductReviewEntity(String productId, Float averageReviewScore, Long numberOfReview) {
        this.productId = productId;
        this.averageReviewScore = averageReviewScore;
        this.numberOfReview = numberOfReview;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Float getAverageReviewScore() {
        return averageReviewScore;
    }

    public void setAverageReviewScore(Float averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    public Long getNumberOfReview() {
        return numberOfReview;
    }

    public void setNumberOfReview(Long numberOfReview) {
        this.numberOfReview = numberOfReview;
    }
}
