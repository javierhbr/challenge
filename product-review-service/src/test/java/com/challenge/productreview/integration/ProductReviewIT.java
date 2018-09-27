package com.challenge.productreview.integration;

import com.challenge.productreview.ProductReviewServiceApplication;
import com.challenge.productreview.dto.ProductReviewDTO;
import com.challenge.productreview.request.ProductReviewRequest;
import com.challenge.productreview.response.ProductReviewResponse;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProductReviewServiceApplication.class)
@RunWith(SpringRunner.class)
public class ProductReviewIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private final String HOST = "http://localhost:";

    private final String REVIEW_ENDPOINT = "/review";
    private final String AUTH_USER = "challenge";
    private final String AUTH_PWD = "code";

    @Test
    public void getProductReview_returnsProductReview() throws Exception {
        String productId = "AC7836";
        String url = HOST + port + REVIEW_ENDPOINT + "/" + productId;

        ResponseEntity<ProductReviewResponse> productResponse = restTemplate.getForEntity(url, ProductReviewResponse.class);

        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
        Assert.assertTrue(productResponse.getBody().getProductReview()!=null);
        Assert.assertTrue(productResponse.getBody().getProductReview().getProductId().equals(productId));
    }

    @Test
    public void getProductReview_returnsReviewNotFound() throws Exception {
        String productId = "M00000";
        String url = HOST + port + REVIEW_ENDPOINT + "/" + productId;

        ResponseEntity<ProductReviewResponse> productResponse = restTemplate.getForEntity(url+"/"+productId, ProductReviewResponse.class);

        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }

    @Test
    public void postProductReview_returnsProductReview() throws Exception {
        String productId = "B42000";
        String url = HOST + port + REVIEW_ENDPOINT;
        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductReview( new ProductReviewDTO(productId, RandomUtils.nextFloat(), RandomUtils.nextLong()));

        ResponseEntity<ProductReviewResponse> productResponse = restTemplate
                .withBasicAuth(AUTH_USER,AUTH_PWD)
                .postForEntity(url, productReviewRequest, ProductReviewResponse.class);

        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test(expected = ResourceAccessException.class)
    public void postProductReview_returns401() throws Exception {
        String productId = "B42000";
        String url = HOST + port + REVIEW_ENDPOINT;
        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductReview( new ProductReviewDTO(productId, RandomUtils.nextFloat(), RandomUtils.nextLong()));

        restTemplate.postForEntity(url, productReviewRequest, ProductReviewResponse.class);
    }

    @Test
    public void putProductReview_returnsProductReview() throws Exception {
        String productId = "BB5476";
        Float averageScore = new Float(99);
        Long numberOfReview = new Long(999);
        String url = HOST + port + REVIEW_ENDPOINT;
        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductReview( new ProductReviewDTO(productId, averageScore, numberOfReview));
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductReviewRequest> reviewRequestHttpEntity = new HttpEntity<>(productReviewRequest, headers);

        ResponseEntity<ProductReviewResponse> productResponse = restTemplate
                .withBasicAuth(AUTH_USER,AUTH_PWD)
                .exchange(url, HttpMethod.PUT, reviewRequestHttpEntity, ProductReviewResponse.class);

        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
        Assert.assertTrue(productResponse.getBody().getProductReview()!=null);
        Assert.assertTrue(productResponse.getBody().getProductReview().getProductId().equals(productId));
        Assert.assertTrue(productResponse.getBody().getProductReview().getAverageScore().equals(averageScore));
        Assert.assertTrue(productResponse.getBody().getProductReview().getNumberOfReview().equals(numberOfReview));
    }

    @Test(expected = ResourceAccessException.class)
    public void putProductReview_returns401() throws Exception {
        String productId = "BB5476";
        Float averageScore = new Float(99);
        Long numberOfReview = new Long(999);
        String url = HOST + port + REVIEW_ENDPOINT;
        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductReview( new ProductReviewDTO(productId, averageScore, numberOfReview));
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductReviewRequest> reviewRequestHttpEntity = new HttpEntity<>(productReviewRequest, headers);

        restTemplate.exchange(url, HttpMethod.PUT, reviewRequestHttpEntity, ProductReviewResponse.class);
    }

    @Test
    public void deleteProductReview_returnsReview() throws Exception {
        String productId = "M20324";
        String url = HOST+ port + REVIEW_ENDPOINT+"/" +productId;
        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductReview( new ProductReviewDTO(productId, RandomUtils.nextFloat(), RandomUtils.nextLong()));
        restTemplate.postForEntity(url, productReviewRequest, ProductReviewResponse.class);

        ResponseEntity productResponse = restTemplate
                .withBasicAuth(AUTH_USER,AUTH_PWD)
                .exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Integer.class, productId);

        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void deleteProductReview_return401() throws Exception {
        String productId = "M20334";
        String url = HOST+ port + REVIEW_ENDPOINT+"/" +productId;
        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductReview( new ProductReviewDTO(productId, RandomUtils.nextFloat(), RandomUtils.nextLong()));

        ResponseEntity productResponse= restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, String.class, productId);

        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.UNAUTHORIZED));
    }
}
