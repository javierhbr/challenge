package com.challenge.productreview.repository;

import com.challenge.productreview.domain.ProductReviewEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *  DAO for product review.
 */
public interface ProductReviewRepository extends CrudRepository<ProductReviewEntity, String> {
}
