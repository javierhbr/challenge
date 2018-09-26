package com.challenge.productservice.component;

import com.challenge.productservice.domain.review.ProductReviewResponse;
import com.challenge.productservice.exception.ProductReviewNotAvailableException;
import com.challenge.productservice.exception.ProductReviewNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.util.ReflectionTestUtils;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProductReviewComponentTest {

    private static final String PRODUCT_ID = "B42000";

    /**
     *  for compilation conflicts when generating the stubbing of the restTemplate .exchange method,
     *  due to the generic ones in the method signature. the mock are inside the class RestTemplateUtilsForTest.java
     */
    private RestTemplateUtilsForTest restTemplate;

    private ProductReviewComponent productReviewComponent = new ProductReviewComponent();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        ReflectionTestUtils.setField(productReviewComponent, "productReviewAPI", "http://localhost:9091/review" );
        restTemplate = new RestTemplateUtilsForTest(PRODUCT_ID);
    }

    @Test
    public void retrieveProductDataForPrductReview_returnProductReview() {
        ReflectionTestUtils.setField(productReviewComponent, "restTemplate", this.restTemplate.returnProductReviewRestTemplate );

        Object productReviewResponse_return = productReviewComponent.retrieveProductData(PRODUCT_ID);

        assertThat(((ProductReviewResponse) productReviewResponse_return).getProductReview().getProductId(), is(PRODUCT_ID));
    }

    @Test
    public void retrieveProductDataForPrductReview_returnProductReviewNotAvailableException() {
        expectedException.expect(ProductReviewNotAvailableException.class);
        ReflectionTestUtils.setField(productReviewComponent, "restTemplate", this.restTemplate.returnResourceAccessException);

        productReviewComponent.retrieveProductData(PRODUCT_ID);
    }

    @Test
    public void retrieveProductDataForPrductReview_returnProductNotFoundException() {
        expectedException.expect(ProductReviewNotFoundException.class);
        ReflectionTestUtils.setField(productReviewComponent, "restTemplate", this.restTemplate.returnProductNotFoundException);

        productReviewComponent.retrieveProductData(PRODUCT_ID);
    }

    @Test
    public void retrieveProductDataForPrductReview_returnResourceAccessExceptionAfterSocketTimeoutException() {
        expectedException.expect(ProductReviewNotAvailableException.class);
        ReflectionTestUtils.setField(productReviewComponent, "restTemplate", this.restTemplate.returnSocketTimeoutException);

        productReviewComponent.retrieveProductData(PRODUCT_ID);
    }

    @Test
    public void retrieveProductDataForPrductReview_returnResourceAccessExceptionAfterConnectTimeoutException() {
        expectedException.expect(ProductReviewNotAvailableException.class);
        ReflectionTestUtils.setField(productReviewComponent, "restTemplate", this.restTemplate.returnConnectTimeoutException);

        productReviewComponent.retrieveProductData(PRODUCT_ID);
    }
}