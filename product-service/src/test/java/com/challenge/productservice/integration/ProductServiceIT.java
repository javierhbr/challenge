package com.challenge.productservice.integration;

import com.challenge.productservice.ProductServiceApplication;
import com.challenge.productservice.response.ProductResponse;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProductServiceApplication.class)
//@RunWith(SpringRunner.class)
public class ProductServiceIT {

    @Autowired

    private TestRestTemplate restTemplate;

    /**
     * Plese Run product-review-service first to execute this test.
     * @throws Exception
     */
//    @Test
    public void getProduct_returnsProductWithReview() throws Exception {
        String productId = "M20324";

        ResponseEntity<ProductResponse> productResponse = restTemplate.getForEntity("/product/".concat(productId), ProductResponse.class);

        assertThat(HttpStatus.OK, is(productResponse.getStatusCode()));
        assertThat(productResponse.getBody().getProduct().getId(), is(productId));
        assertThat(productResponse.getBody().getMessage(), nullValue());
    }

    /**
     * Plese Run product-review-service first to execute this test.
     * @throws Exception
     */
//    @Test
    public void getProduct_returnsProductWithOutReview() throws Exception {
        String productId = "B42000";

        ResponseEntity<ProductResponse> productResponse = restTemplate.getForEntity("/product/".concat(productId), ProductResponse.class);

        assertThat(productResponse.getStatusCode(), is(HttpStatus.OK));
        assertThat(productResponse.getBody().getProduct().getId(), is(productId));
        assertThat(productResponse.getBody().getMessage(), StringContains.containsString("Error"));
    }

//    @Test
    public void getProduct_returnsProductNotFound() throws Exception {
        String productId = "M2032435";
        ResponseEntity<ProductResponse> productResponse = restTemplate.getForEntity("/product/".concat(productId), ProductResponse.class);

        assertThat(productResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

}
