package com.challenge.productservice.component;

import com.challenge.productservice.domain.product.Product;
import com.challenge.productservice.exception.ProductNotFoundException;
import com.challenge.productservice.exception.ProductResourceException;
import io.netty.channel.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import java.net.SocketTimeoutException;

@Component
public class ProductDetailsComponent extends AbstractProductComponent<Product> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${external.resources.api.endpoint.product-details}")
    private String productDetailsApi;

    public ProductDetailsComponent() {
        super(Product.class);

    }

    @Override
    protected void errorHandler(RestClientException ex, String productId) {

        if (ex instanceof ResourceAccessException) {
            logger.error("Could not get product details with id:  {}: cause:{}", productId, ex.getMessage());
            throw new ProductResourceException("Could not get product details with id: " + productId);

        } else if (ex instanceof HttpStatusCodeException) {
            HttpStatusCodeException httpException = (HttpStatusCodeException) ex;
            if (httpException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                logger.error("Could not find any product with id: {}: cause:{}", productId, ex.getMessage());
                throw new ProductNotFoundException("Could not find any product with id: " + productId);
            }
        }else if (ex.getCause() instanceof SocketTimeoutException) {
            logger.error("Could not get product details with id:  {}: cause:{}", productId, ex.getMessage());
            throw new ProductResourceException("Could not get product details with id: " + productId);

        }else if (ex.getCause() instanceof ConnectTimeoutException) {
            logger.error("Could not get product details with id:  {}: cause:{}", productId, ex.getMessage());
            throw new ProductResourceException("Could not get product details with id: " + productId);
        }
    }





    @Override
    protected String getHostUrl() {
        return this.productDetailsApi;
    }

}
