package com.challenge.productservice.service;

import com.challenge.productservice.component.ProductDetailsComponent;
import com.challenge.productservice.component.ProductReviewComponent;
import com.challenge.productservice.domain.product.Product;
import com.challenge.productservice.domain.review.ProductReview;
import com.challenge.productservice.exception.ProductNotFoundException;
import com.challenge.productservice.exception.ProductReviewNotFoundException;
import com.challenge.productservice.response.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ProductReviewComponent productReviewComponent;

    private ProductDetailsComponent productDetailsComponent;

    public ProductServiceImpl(ProductReviewComponent productReviewComponent, ProductDetailsComponent productDetailsComponent) {
        this.productReviewComponent = productReviewComponent;
        this.productDetailsComponent = productDetailsComponent;
    }

    @Override
    public ProductResponse getProductById(String productId) throws ProductNotFoundException {

        logger.debug("getting information for productId:{}", productId);
        ProductResponse response = new ProductResponse();
        //get product details
        Product product = productDetailsComponent.retrieveProductDetails(productId);

        //get product review
        try {
            ProductReview productReview = productReviewComponent.retrieveProductReview(productId);
            product.setProductReview(productReview);

        }catch (ProductReviewNotFoundException ex){
            response.setMessage(ex.getMessage());
        }

        response.setProduct(product);
        return response;
    }
}
