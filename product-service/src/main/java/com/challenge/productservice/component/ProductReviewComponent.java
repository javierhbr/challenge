package com.challenge.productservice.component;


import com.challenge.productservice.domain.review.ProductReview;
import com.challenge.productservice.domain.review.ProductReviewResponse;
import com.challenge.productservice.exception.ProductReviewNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductReviewComponent {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;

    @Value("${external.resources.api.endpoint.product-review}")
    private String productReviewAPI;

    public ProductReviewComponent(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ProductReview retrieveProductReview(String productId) throws ProductReviewNotFoundException {

        logger.debug("getting product review for {}", productId);
        ProductReview productReview = null;
        String url = productReviewAPI + "/" + productId;

        try {
            ResponseEntity<ProductReviewResponse> responseResponseEntity =
                    restTemplate.getForEntity(url, ProductReviewResponse.class);

            if(!responseResponseEntity.getStatusCode().equals(HttpStatus.OK)){
                logger.error("product review was not found for productId:{} , response :{}"
                        , productId
                        , responseResponseEntity.getBody().toString());
                throw new ProductReviewNotFoundException("Error getting product review of " + productId);
            }
            productReview = responseResponseEntity.getBody().getProductReview();

        }catch (RestClientException ex){
            logger.error("Error getting product review of {}:", productId, ex);
            throw new ProductReviewNotFoundException("Error getting product review of " + productId);
        }
        return productReview;
    }

}
