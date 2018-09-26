package com.challenge.productreview.mapper;

import com.challenge.productreview.domain.ProductReviewEntity;
import com.challenge.productreview.dto.ProductReviewDTO;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class ProductReviewMapperTest {

    @Test
    public void givenProductReviewEntuty_returnProductReviewDto() {

        String productId = "B42000";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewEntity productReviewEntity = new ProductReviewEntity(productId, averageScore,  numberOfReview);

        ProductReviewDTO  productReviewDto = ProductReviewMapper.ProductReviewEntityToDto(productReviewEntity);

        assertThat(productId, is(productReviewDto.getProductId()));
        assertThat(averageScore, is(productReviewDto.getAverageScore()));
        assertThat(numberOfReview, is(productReviewDto.getNumberOfReview()));

    }

    @Test
    public void givenProductReviewDto_returnProductReviewEntity() {

        String productId = "B42000";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewDTO productReviewDto = new ProductReviewDTO(productId, averageScore,  numberOfReview);

        ProductReviewEntity  reviewEntity = ProductReviewMapper.ProductReviewDtoToEntity(productReviewDto);

        assertThat(productId, is(reviewEntity.getProductId()));
        assertThat(averageScore, is(reviewEntity.getAverageReviewScore()));
        assertThat(numberOfReview, is(reviewEntity.getNumberOfReview()));

    }

    @Test
    public void givenNullProductReviewEntity_returnNull() {

        ProductReviewEntity productReviewEntity = null;

        ProductReviewDTO  productReviewDto = ProductReviewMapper.ProductReviewEntityToDto(productReviewEntity);

        assertThat(productReviewDto, nullValue());
    }

    @Test
    public void givenNullProductReviewDto_returnNull() {

        ProductReviewDTO productReviewDto = null;

        ProductReviewEntity  reviewEntity = ProductReviewMapper.ProductReviewDtoToEntity(productReviewDto);

        assertThat(reviewEntity, nullValue());

    }
}