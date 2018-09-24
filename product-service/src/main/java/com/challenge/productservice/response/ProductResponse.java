package com.challenge.productservice.response;

import com.challenge.productservice.domain.product.Product;

public class ProductResponse {

    private Product product;
    private String  message;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
