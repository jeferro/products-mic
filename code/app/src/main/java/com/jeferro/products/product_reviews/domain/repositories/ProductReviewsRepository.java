package com.jeferro.products.product_reviews.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.domain.models.ProductId;

public interface ProductReviewsRepository {

  ProductReviews findAllByProductId(ProductId productId);

  Optional<ProductReview> findById(ProductReviewId productReviewId);

  default ProductReview findByIdOrError(ProductReviewId productReviewId) {
	return findById(productReviewId)
		.orElseThrow(() -> ProductReviewNotFoundException.createOf(productReviewId));
  }

  void save(ProductReview productReview);

  void deleteById(ProductReviewId productReviewId);

  void deleteAllById(List<ProductReviewId> productReviewIds);
}
