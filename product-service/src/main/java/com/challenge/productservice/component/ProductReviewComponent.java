package com.challenge.productservice.component;

import com.challenge.productservice.domain.review.ProductReviewResponse;
import com.challenge.productservice.exception.ProductReviewNotAvailableException;
import com.challenge.productservice.exception.ProductReviewNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

@Component
public class ProductReviewComponent extends AbstractProductComponent<ProductReviewResponse> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${external.resources.api.endpoint.product-review}")
    private String productReviewAPI;

    public ProductReviewComponent() {
       super(ProductReviewResponse.class);
    }

    @Override
    protected void errorHandler(RestClientException ex, String productId) {
        if (ex instanceof ResourceAccessException) {
            throw new ProductReviewNotAvailableException("Product review is it not available at this time for productId " + productId);

        } else if (ex instanceof HttpStatusCodeException) {
            HttpStatusCodeException httpException = (HttpStatusCodeException) ex;
            if (httpException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                logger.error("Error getting product review of {}: cause:{}", productId, ex.getMessage());
                throw new ProductReviewNotFoundException("Error getting product review of " + productId);
            }
        }
    }

    @Override
    protected String getHostUrl() {
        return this.productReviewAPI;
    }

}
