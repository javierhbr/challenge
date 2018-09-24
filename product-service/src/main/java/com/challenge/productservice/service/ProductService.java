package com.challenge.productservice.service;

import com.challenge.productservice.response.ProductResponse;

public interface ProductService {

    ProductResponse getProductById(String productId);
}
