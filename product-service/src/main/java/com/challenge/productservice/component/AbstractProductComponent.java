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
            // add an empty header to get response from API addidas.com.uk and works for review API
            HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
            requestHeaders.add("Cookie", "ak_bmsc=6DCA65A10A56C388FDF179BDE32ED28E17D70F1C34700000D97EAA5BA3733E10~plg+SI+wQo05z+jFXuF0CxAD4b0nWCZm+lXgRjWzmmeUEKrpbUNe7tm87Lc/cVoMHmJYxK2ZlxE6r9xrEd6CxmIQPOcx/reBYnE2dtUW9kwikED9doS+45hNpb8k/cwYowdVKfL5+WaHEMvnuyeRcedy/wjqakLzU1v5BruPODcylwPF1p5FFbe8mTisHkwsGFpO9sXWDp0cvnXFAQhKTBN0NyIt+zQiFMqFfoCeG9vvM=;");
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
