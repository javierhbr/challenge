package com.challenge.productreview.service;

import com.challenge.productreview.domain.ProductReviewEntity;
import com.challenge.productreview.dto.ProductReviewDTO;
import com.challenge.productreview.exception.ProductReviewNotFoundException;
import com.challenge.productreview.repository.ProductReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;



@RunWith(MockitoJUnitRunner.class)
public class ProductReviewServiceImplTest {

    @Mock
    private ProductReviewRepository productReviewRepository;


    private ProductReviewServiceImpl productReviewService;

    @Before
    public void setUp() throws Exception {
        productReviewService = new ProductReviewServiceImpl(productReviewRepository);
    }

    @Test
    public void getProductReviewById_returnProductReview() {
        String productId = "B42000";
        Float averageScore = new Float(99);
        Long numberOfReview = new Long(999);
        ProductReviewEntity productReviewEntity_getById = new ProductReviewEntity(productId, averageScore,  numberOfReview);

        /*Mockito.when(this.productReviewRepository.findById(ArgumentMatchers.anyString())
                .orElseThrow(() -> new ProductReviewNotFoundException("Could not find entity with id: " + productId)))
                .thenReturn(productReviewEntity_getById);
*/
       // Mockito.when(productReviewService.getProductReviewEntityByProductId(ArgumentMatchers.anyString())).thenReturn(productReviewEntity_getById);

        BDDMockito.given(productReviewService.getProductReviewEntityByProductId(ArgumentMatchers.anyString()))
                .willReturn(productReviewEntity_getById);


        ProductReviewDTO reviewDTO = productReviewService.getProductReviewById(productId);
       // assertEquals(productId, reviewDTO.getProductId());
    }

    @Test
    public void createProductReview() {
    }

    @Test
    public void updateProductReviewById() {
    }

    @Test
    public void deleteProductReviewById() {
    }
}