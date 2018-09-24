package com.challenge.productservice.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractProductComponent<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<T> entityClass;

    @Autowired
    private RestTemplate restTemplate;

    protected AbstractProductComponent(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T retrieveProductData(String productId){

        logger.debug("getting product review for {}", productId);
        T product = null;
        String url = this.getHostUrl() + "/" + productId;
        HttpHeaders requestHeaders = new HttpHeaders();
        try {
            HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
            ResponseEntity<T> responseResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, this.entityClass);

            if(responseResponseEntity.getStatusCode().equals(HttpStatus.OK)){
                logger.info("successfully data for product :{}", productId);
            }

            product = responseResponseEntity.getBody();

        } catch (RestClientException ex){
            logger.error("Error getting product details of {}, message:{} ", productId, ex.getMessage());
            errorHandler(ex, productId);
        }
        return product;
    }

    protected abstract void errorHandler(RestClientException ex, String productId);

    protected abstract String getHostUrl();

}
