package com.challenge.productreview.service;

import com.challenge.productreview.domain.ProductReviewEntity;
import com.challenge.productreview.dto.ProductReviewDTO;
import com.challenge.productreview.exception.ProductReviewNotFoundException;
import com.challenge.productreview.mapper.ProductReviewMapper;
import com.challenge.productreview.repository.ProductReviewRepository;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Optional;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductReviewServiceTest {

    @Mock
    private ProductReviewRepository productReviewRepository;

    @InjectMocks
    private ProductReviewServiceImpl productReviewService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getProductReviewById_returnProductReview() {
        String productId = "B42000";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewEntity productReviewEntity_getById = new ProductReviewEntity(productId, averageScore,  numberOfReview);
        when(productReviewRepository.findById(anyString()))
                .thenReturn(Optional.of(productReviewEntity_getById));

        ProductReviewDTO reviewDTO = productReviewService.getProductReviewById(productId);

        assertThat(productId, is(reviewDTO.getProductId()));
        verify(productReviewRepository)
                .findById(productId);
    }

    @Test
    public void getProductReviewById_returnNotFoundException() {
        expectedException.expect(ProductReviewNotFoundException.class);
        String productId = "B42000";
        when(productReviewRepository.findById(anyString()))
                .thenThrow(ProductReviewNotFoundException.class);

        ProductReviewDTO reviewDTO = productReviewService.getProductReviewById(productId);

        verify(productReviewRepository)
                .findById(productId);
    }

    @Test
    public void createProductReview_returnNewReview() {
        String productId = "B42000";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewDTO productReviewDtoCreate = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        ProductReviewEntity productReviewEntity_created = ProductReviewMapper.ProductReviewDtoToEntity(productReviewDtoCreate);
        when(productReviewRepository.save(any()))
                .thenReturn(productReviewEntity_created);

        ProductReviewDTO reviewDTO = productReviewService.createProductReview(productReviewDtoCreate);

        assertThat(productId, is(reviewDTO.getProductId()));
        assertThat(averageScore, is(reviewDTO.getAverageScore()));
        assertThat(numberOfReview, is(reviewDTO.getNumberOfReview()));
        verify(productReviewRepository).save(any(ProductReviewEntity.class));
    }

    @Test
    public void createProductReview_returnDuplicateException() {
        expectedException.expect(DuplicateKeyException.class);
        String productId = "B42000";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewDTO productReviewDtoCreate = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        when(productReviewRepository.save(any()))
                .thenThrow(DuplicateKeyException.class);

        productReviewService.createProductReview(productReviewDtoCreate);

        verify(productReviewRepository).save(new ProductReviewEntity());
    }

    @Test
    public void updateProductReviewById_returnNewValues() {
        String productId = "B5000";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview =RandomUtils.nextLong();
        ProductReviewDTO productReviewDto_toUpdate = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        ProductReviewEntity productReviewEntity_beforeUpdate = new ProductReviewEntity(productId, RandomUtils.nextFloat(),   RandomUtils.nextLong());
        ProductReviewEntity productReviewEntity_updated = ProductReviewMapper.ProductReviewDtoToEntity(productReviewDto_toUpdate);
        when(productReviewRepository.findById(anyString()))
                .thenReturn(Optional.of(productReviewEntity_beforeUpdate));
        when(productReviewRepository.save(any(ProductReviewEntity.class)))
                .thenReturn(productReviewEntity_updated);

        ProductReviewDTO reviewDTO = productReviewService.updateProductReviewById(productReviewDto_toUpdate);

        assertThat(productId, is(reviewDTO.getProductId()));
        assertThat(averageScore, is(reviewDTO.getAverageScore()));
        assertThat(numberOfReview, is(reviewDTO.getNumberOfReview()));
        verify(productReviewRepository).findById(productId);
        verify(productReviewRepository).save(productReviewEntity_beforeUpdate);
    }

    @Test
    public void updateProductReviewById_returnNotFoundException() {
        expectedException.expect(ProductReviewNotFoundException.class);
        String productId = "B5000";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview =RandomUtils.nextLong();
        ProductReviewDTO productReviewDto_toUpdate = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        when(productReviewRepository
                .findById(anyString()))
                .thenThrow(new ProductReviewNotFoundException(""));

        productReviewService.updateProductReviewById(productReviewDto_toUpdate);

        verify(productReviewRepository).findById(productId);
    }

    @Test
    public void deleteProductReviewById() {
        String productId = "B42000";
        doAnswer((Answer) action -> {
                return null;
            }).when(productReviewRepository)
                .deleteById(anyString());

        productReviewService.deleteProductReviewById(productId);

        verify(productReviewRepository).deleteById(productId);
    }

    @Test
    public void deleteProductReviewById_returnException() {
        expectedException.expect(EmptyResultDataAccessException.class);
        String productId = "B42000";
        doThrow(EmptyResultDataAccessException.class)
                .when(productReviewRepository)
                .deleteById(anyString());

        productReviewService.deleteProductReviewById(productId);

        verify(productReviewRepository).deleteById(productId);
    }
}