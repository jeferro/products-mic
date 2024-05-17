package com.jeferro.products.product_reviews.domain.repositories;

import java.util.Optional;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;

public interface ProductReviewsRepository {

  Optional<ProductReview> findById(ProductReviewId productReviewId);

  void save(ProductReview productReview);
}