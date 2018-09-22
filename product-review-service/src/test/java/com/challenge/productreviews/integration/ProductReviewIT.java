package com.challenge.productreviews.integration;

import com.challenge.productreview.response.ProductReviewResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        //Act
        ResponseEntity<ProductReviewResponse> productResponse = restTemplate.getForEntity(REVIEW_ENDPOINT+"/"+productId, ProductReviewResponse.class);

        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
//        assertThat(productResponse.getBody().getName()).isEqualTo("prius");
//        assertThat(productResponse.getBody().getType()).isEqualTo("hybrid");
    }

    @Test
    public void getProductReview_returnsReviewNotFound() throws Exception {
        //Arrange

        String productId = "M20324";
        //Act
        ResponseEntity<ProductReviewResponse> productResponse = restTemplate.getForEntity(REVIEW_ENDPOINT+"/"+productId, ProductReviewResponse.class);

        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }


    @Test
    public void postProductReview_returnsProductReview() throws Exception {
        //Arrange

        String productId = "M20324";
        ProductReviewResponse ProductReviewResponse = new ProductReviewResponse();
        //Act
        ResponseEntity<ProductReviewResponse> productResponse =
                restTemplate.postForEntity(REVIEW_ENDPOINT, ProductReviewResponse, ProductReviewResponse.class);
        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
//        assertThat(productResponse.getBody().getName()).isEqualTo("prius");
//        assertThat(productResponse.getBody().getType()).isEqualTo("hybrid");
    }

    @Test
    public void putProductReview_returnsProductReview() throws Exception {
        //Arrange

        String url = HOST+ port + REVIEW_ENDPOINT;
        String productId = "M20324";

        ProductReviewResponse ProductReviewResponse = new ProductReviewResponse();

        //Act
        HttpEntity<ProductReviewResponse> productReviewHttpEntity = new HttpEntity<ProductReviewResponse>(ProductReviewResponse);


        ResponseEntity<ProductReviewResponse> productResponse =
                restTemplate.exchange(url,HttpMethod.PUT,productReviewHttpEntity, ProductReviewResponse.class);
      //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
//        assertThat(productResponse.getBody().getName()).isEqualTo("prius");
//        assertThat(productResponse.getBody().getType()).isEqualTo("hybrid");
    }

    @Test
    public void deleteProductReview_returnsReview() throws Exception {
        //Arrange

        String productId = "M20324";
        String url = HOST+ port + REVIEW_ENDPOINT+"/{productId}";
        //Act
        restTemplate.delete(url,productId );

        //Assert
        ResponseEntity<Integer> result = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Integer.class, productId);

        Assert.assertTrue(result.getBody()==3);

    }
}
