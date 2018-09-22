package com.challenge.productreview.service;

import com.challenge.productreview.domain.ProductReviewEntity;
import com.challenge.productreview.dto.ProductReviewDTO;
import com.challenge.productreview.exception.ProductReviewNotFoundException;
import com.challenge.productreview.mapper.ProductReviewMapper;
import com.challenge.productreview.repository.ProductReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    private static Logger LOG = LoggerFactory.getLogger(ProductReviewServiceImpl.class);

    private ProductReviewRepository productReviewRepository;

    public ProductReviewServiceImpl(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    @Override
    public ProductReviewDTO getProductReviewById(String productId) throws ProductReviewNotFoundException {

        ProductReviewEntity  reviewEntity= this.productReviewRepository.findById(productId)
                .orElseThrow(() -> new ProductReviewNotFoundException("Could not find entity with id: " + productId));

        return ProductReviewMapper.ProductReviewEntityToDto(reviewEntity);
    }

    @Override
    public ProductReviewDTO createProductReview(ProductReviewDTO reviewDTO) {
        return null;
    }

    @Override
    public ProductReviewDTO updateProductReviewById(ProductReviewDTO reviewDTO) throws ProductReviewNotFoundException {
        return null;
    }

    @Override
    public ProductReviewDTO deleteProductReviewById(String productId) throws ProductReviewNotFoundException {
        return null;
    }
}
