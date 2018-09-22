package com.challenge.productreview.mapper;

import com.challenge.productreview.domain.ProductReviewEntity;
import com.challenge.productreview.dto.ProductReviewDTO;

public class ProductReviewMapper {


    public static ProductReviewEntity ProductReviewDtoToEntity(ProductReviewDTO reviewDTO){
        ProductReviewEntity review = new ProductReviewEntity();

        if(reviewDTO == null){
            return null;
        }
        review.setAverageReviewScore(reviewDTO.getAverageScore());
        review.setNumberOfReview(reviewDTO.getNumberOfReview());
        review.setProductId(reviewDTO.getProductId());

        return review;
    }

    public static ProductReviewDTO ProductReviewEntityToDto(ProductReviewEntity reviewEntity){
        ProductReviewDTO reviewDTO = new ProductReviewDTO();

        if(reviewEntity == null){
            return null;
        }
        reviewDTO.setAverageScore(reviewEntity.getAverageReviewScore());
        reviewDTO.setNumberOfReview(reviewEntity.getNumberOfReview());
        reviewDTO.setProductId(reviewEntity.getProductId());

        return reviewDTO;
    }
}


