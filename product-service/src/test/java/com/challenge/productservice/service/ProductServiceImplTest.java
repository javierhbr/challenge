package com.challenge.productservice.service;

import com.challenge.productservice.component.ProductDetailsComponent;
import com.challenge.productservice.component.ProductReviewComponent;
import com.challenge.productservice.domain.product.Product;
import com.challenge.productservice.domain.review.ProductReview;
import com.challenge.productservice.domain.review.ProductReviewResponse;
import com.challenge.productservice.exception.ProductNotFoundException;
import com.challenge.productservice.exception.ProductResourceException;
import com.challenge.productservice.exception.ProductReviewNotFoundException;
import com.challenge.productservice.response.ProductResponse;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.StringContains.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductReviewComponent productReviewComponent;

    @Mock
    private ProductDetailsComponent productDetailsComponent;

    @InjectMocks
    private ProductServiceImpl productService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getProductById_returnProductResourceException() {
        expectedException.expect(ProductResourceException.class);
        String productId = "B42000";

        when(productDetailsComponent.retrieveProductData(anyString()))
                .thenThrow(ProductResourceException.class);

        productService.getProductById(productId);
    }

    @Test
    public void getProductById_returnProductNotFoundException() {
        expectedException.expect(ProductNotFoundException.class);
        String productId = "B42000";

        when(productDetailsComponent.retrieveProductData(anyString()))
                .thenThrow(ProductNotFoundException.class);

        productService.getProductById(productId);
    }

    @Test
    public void getProductById_returnProductWithReview() {
        String productId = "B42000";
        String name = "ProductNameTest";
        String modelNumber = "B42000B42000";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewResponse reviewResponse_mock = new ProductReviewResponse();
        Product product_mock = new Product(productId, name, modelNumber);
        ProductReview productReview_mock = new ProductReview(productId, averageScore, numberOfReview);
        product_mock.setProductReview(productReview_mock);
        reviewResponse_mock.setProductReview(productReview_mock);

        when(productDetailsComponent.retrieveProductData(anyString()))
               .thenReturn(product_mock);
        when(productReviewComponent.retrieveProductData(anyString()))
                .thenReturn(reviewResponse_mock);

        ProductResponse productResponse = productService.getProductById(productId);

        assertThat(productId, is(productResponse.getProduct().getId()));
        verify(productDetailsComponent)
                .retrieveProductData(productId);
    }

    @Test
    public void getProductById_returnProductWithThrowProductReviewNotFoundException() {
        String productId = "AC7836";
        String name = "ProductNameTest";
        String modelNumber = "AC7836-AC7836";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewResponse reviewResponse_mock = new ProductReviewResponse();
        Product product_mock = new Product(productId, name, modelNumber);
        ProductReview productReview_mock = new ProductReview(productId, averageScore, numberOfReview);
        product_mock.setProductReview(productReview_mock);
        reviewResponse_mock.setProductReview(productReview_mock);
        when(productDetailsComponent.retrieveProductData(anyString()))
                .thenReturn(product_mock);
        when(productReviewComponent.retrieveProductData(anyString()))
                .thenThrow(new ProductReviewNotFoundException("Error getting product review of " + productId));

        ProductResponse productResponse = productService.getProductById(productId);

        assertThat(productResponse.getProduct().getId(), is(productId));
        assertThat(productResponse.getMessage(), containsString("Error getting product"));
        verify(productDetailsComponent).retrieveProductData(productId);
        verify(productReviewComponent).retrieveProductData(productId);
    }

    public void getProductById_returnProductWithThrowProductReviewNotAvailableException() {
        String productId = "AC7836";
        String name = "ProductNameTest";
        String modelNumber = "AC7836-AC7836";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewResponse reviewResponse_mock = new ProductReviewResponse();
        Product product_mock = new Product(productId, name, modelNumber);
        ProductReview productReview_mock = new ProductReview(productId, averageScore, numberOfReview);
        product_mock.setProductReview(productReview_mock);
        reviewResponse_mock.setProductReview(productReview_mock);
        when(productDetailsComponent.retrieveProductData(anyString()))
                .thenReturn(product_mock);
        when(productReviewComponent.retrieveProductData(anyString()))
                .thenThrow(new ProductReviewNotFoundException("Product review is it not available at this time for productId " + productId));

        ProductResponse productResponse = productService.getProductById(productId);

        assertThat(productResponse.getProduct().getId(), is(productId));
        assertThat(productResponse.getMessage(), containsString("not available"));
        verify(productDetailsComponent).retrieveProductData(productId);
        verify(productReviewComponent).retrieveProductData(productId);
    }

}