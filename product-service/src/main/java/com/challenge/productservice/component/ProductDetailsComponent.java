package com.challenge.productservice.component;

import com.challenge.productservice.domain.product.Product;
import com.challenge.productservice.exception.ProductNotFoundException;
import com.challenge.productservice.exception.ProductResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductDetailsComponent {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${external.resources.api.endpoint.product-details}")
    private String productDetailsApi;

    private RestTemplate restTemplate;

    public ProductDetailsComponent(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product retrieveProductDetails(String productId){

        logger.debug("getting product review for {}", productId);
        Product product = null;
        String url = productDetailsApi + "/" + productId;
        HttpHeaders requestHeaders = new HttpHeaders();
        try {
            HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
            ResponseEntity<Product> responseResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Product.class);

            if(responseResponseEntity.getStatusCode().equals(HttpStatus.OK)){
                logger.info("successfully details for product :{}", productId);
            }

            product = responseResponseEntity.getBody();

        }catch (RestClientException ex){
            logger.error("Error getting product details of {}, message:{} ", productId, ex.getMessage());
            errorHandler(ex, productId);

        }
        return product;
    }

    private void errorHandler(RestClientException ex, String productId) {

        if (ex instanceof ResourceAccessException) {
            throw new ProductResourceException("Could not get product details with id: " + productId);

        } else if (ex instanceof HttpStatusCodeException) {
            final HttpStatusCodeException httpException = (HttpStatusCodeException) ex;
            // handle 404 NOT FOUND error
            if (httpException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ProductNotFoundException("Could not find any product with id: " + productId);
            }
        }
    }

}
