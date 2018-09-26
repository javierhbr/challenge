package com.challenge.productservice.component;

import com.challenge.productservice.domain.product.Product;
import com.challenge.productservice.domain.review.ProductReview;
import com.challenge.productservice.domain.review.ProductReviewResponse;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

/**
 *  This class contain all the mocks for RestTemplate.exchange method used by AbstractProductComponent class
 */
public class RestTemplateUtilsForTest {

    private String productId;

    public RestTemplateUtilsForTest(String productId) {
        this.productId = productId;
    }

    public RestTemplate returnProductDetailsRestTemplate = new RestTemplate(){
        @Override
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                              HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
                throws RestClientException {
            Product product_mock  = new Product();
            product_mock.setId(productId);
            ResponseEntity<T> responseEntity = new ResponseEntity(product_mock, HttpStatus.OK);
            return responseEntity;
        }
    };

    public RestTemplate returnProductReviewRestTemplate = new RestTemplate(){
        @Override
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                              HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
                throws RestClientException {
            ProductReviewResponse productReview_mock = new ProductReviewResponse();
            productReview_mock.setProductReview(new ProductReview(productId, RandomUtils.nextFloat(), RandomUtils.nextLong()));
            ResponseEntity<T> responseEntity = new ResponseEntity(productReview_mock, HttpStatus.OK);

            return responseEntity;
        }
    };

    public RestTemplate returnResourceAccessException = new RestTemplate(){
        @Override
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                              HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
                throws RestClientException {
            throw new ResourceAccessException(StringUtils.EMPTY);
        }

    };

    public RestTemplate returnSocketTimeoutException = new RestTemplate(){
        @Override
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                              HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
                throws RestClientException {
            throw new RestClientException(StringUtils.EMPTY,new SocketTimeoutException());
        }

    };

    public RestTemplate returnConnectTimeoutException = new RestTemplate(){
        @Override
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                              HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
                throws RestClientException {
            throw new RestClientException(StringUtils.EMPTY,new ConnectTimeoutException());
        }

    };

    public RestTemplate returnProductNotFoundException = new RestTemplate(){
        @Override
        public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
                                              HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables)
                throws RestClientException {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

    };
}
