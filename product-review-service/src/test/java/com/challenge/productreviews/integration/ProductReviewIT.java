package com.challenge.productreviews.integration;

import com.challenge.productreview.ProductReviewServiceApplication;
import com.challenge.productreview.dto.ProductReviewDTO;
import com.challenge.productreview.request.ProductReviewRequest;
import com.challenge.productreview.response.ProductReviewResponse;
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

    @Test
    public void getProductReview_returnsProductReview() throws Exception {
        //Arrange
        String productId = "M20324";
        String url = HOST + port + REVIEW_ENDPOINT + "/" + productId;
        //Act
        ResponseEntity<ProductReviewResponse> productResponse = restTemplate.getForEntity(url, ProductReviewResponse.class);

        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
        Assert.assertTrue(productResponse.getBody().getProductReview()!=null);
        Assert.assertTrue(productResponse.getBody().getProductReview().getProductId().equals(productId));
    }

    @Test
    public void getProductReview_returnsReviewNotFound() throws Exception {
        //Arrange
        String productId = "M00000";
        String url = HOST + port + REVIEW_ENDPOINT + "/" + productId;

        //Act
        ResponseEntity<ProductReviewResponse> productResponse = restTemplate.getForEntity(url+"/"+productId, ProductReviewResponse.class);

        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }

    @Test
    public void postProductReview_returnsProductReview() throws Exception {
        //Arrange

        String productId = "POST000";
        String url = HOST + port + REVIEW_ENDPOINT;
        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductReview( new ProductReviewDTO(productId, new Float(1), new Long(1)));
        //Act
        ResponseEntity<ProductReviewResponse> productResponse =
                restTemplate.postForEntity(url, productReviewRequest, ProductReviewResponse.class);
        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test
    public void putProductReview_returnsProductReview() throws Exception {
        //Arrange
        String productId = "B42000";
        Float averageScore = new Float(99);
        Long numberOfReview = new Long(999);
        String url = HOST + port + REVIEW_ENDPOINT;

        ProductReviewRequest productReviewRequest = new ProductReviewRequest();
        productReviewRequest.setProductReview( new ProductReviewDTO(productId, averageScore, numberOfReview));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductReviewRequest> reviewRequestHttpEntity = new HttpEntity<>(productReviewRequest, headers);

        //Act
        ResponseEntity<ProductReviewResponse> productResponse = restTemplate.exchange(url, HttpMethod.PUT, reviewRequestHttpEntity, ProductReviewResponse.class);

        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
        Assert.assertTrue(productResponse.getBody().getProductReview()!=null);
        Assert.assertTrue(productResponse.getBody().getProductReview().getProductId().equals(productId));
        Assert.assertTrue(productResponse.getBody().getProductReview().getAverageScore().equals(averageScore));
        Assert.assertTrue(productResponse.getBody().getProductReview().getNumberOfReview().equals(numberOfReview));
    }

    @Test
    public void deleteProductReview_returnsReview() throws Exception {
        //Arrange

        String productId = "M20324";
        String url = HOST+ port + REVIEW_ENDPOINT+"/{productId}";
        //Act
        restTemplate.delete(url,productId );

        //Assert
        ResponseEntity<Integer> productResponse = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Integer.class, productId);

        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));

    }
}
