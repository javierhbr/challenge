package com.challenge.productservice.component;

import com.challenge.productservice.domain.product.Product;
import com.challenge.productservice.exception.ProductNotFoundException;
import com.challenge.productservice.exception.ProductResourceException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ProductDetailsComponentTest {

    private static final String PRODUCT_ID = "B42000";

    /**
     *  por comflictos de de compilacion de genereic el stubbing se hace asi...
     */
    private RestTemplateUtilsForTest restTemplate;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ProductDetailsComponent productDetailsComponent = new ProductDetailsComponent();

    @Before
    public void setup(){
        ReflectionTestUtils.setField(productDetailsComponent, "productDetailsApi", "https://www.adidas.co.uk/api/products" );
        restTemplate = new RestTemplateUtilsForTest(PRODUCT_ID);
    }

    @Test
    public void retrieveProductDataForProductDetails_returnProductDetails() {
        ReflectionTestUtils.setField(productDetailsComponent, "restTemplate", this.restTemplate.returnProductDetailsRestTemplate );

        Object productReviewResponse_return = productDetailsComponent.retrieveProductData(PRODUCT_ID);

        assertThat(((Product) productReviewResponse_return).getId(), is(PRODUCT_ID));
    }

    @Test
    public void retrieveProductDataForProductDetails_returnResourceAccessException() {
        expectedException.expect(ProductResourceException.class);
        ReflectionTestUtils.setField(productDetailsComponent, "restTemplate", this.restTemplate.returnResourceAccessException);

        productDetailsComponent.retrieveProductData(PRODUCT_ID);
    }

    @Test
    public void retrieveProductDataForProductDetails_returnProductNotFoundException() {
        expectedException.expect(ProductNotFoundException.class);
        ReflectionTestUtils.setField(productDetailsComponent, "restTemplate", this.restTemplate.returnProductNotFoundException);

        productDetailsComponent.retrieveProductData(PRODUCT_ID);
    }


    @Test
    public void retrieveProductDataForProductDetails_returnResourceAccessExceptionAfterConnectTimeoutException() {
        expectedException.expect(ProductResourceException.class);
        ReflectionTestUtils.setField(productDetailsComponent, "restTemplate", this.restTemplate.returnConnectTimeoutException);

        productDetailsComponent.retrieveProductData(PRODUCT_ID);
    }

    @Test
    public void retrieveProductDataForProductDetails_returnResourceAccessExceptionAfterSocketTimeoutException() {
        expectedException.expect(ProductResourceException.class);
        ReflectionTestUtils.setField(productDetailsComponent, "restTemplate", this.restTemplate.returnSocketTimeoutException);

        productDetailsComponent.retrieveProductData(PRODUCT_ID);
    }


}