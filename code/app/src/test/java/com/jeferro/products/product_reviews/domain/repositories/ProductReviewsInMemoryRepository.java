package com.jeferro.products.product_reviews.domain.repositories;

import java.util.List;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;

public class ProductReviewsInMemoryRepository extends InMemoryRepository<ProductReview, ProductReviewId>
	implements ProductReviewsRepository {

  @Override
  public ProductReviews findAllByProductId(ProductId productId) {
	var products = data.values().stream()
		.filter(productReview -> productReview.getProductId().equals(productId))
		.toList();

	return new ProductReviews(products);
  }

  @Override
  public void deleteAllById(List<ProductReviewId> productReviewIds) {
	productReviewIds.forEach(this::deleteById);
  }
}