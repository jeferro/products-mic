package com.jeferro.products.product_reviews.domain.models;

import java.util.Arrays;
import java.util.List;

import com.jeferro.shared.domain.models.aggregates.EntityCollection;

public class ProductReviews extends EntityCollection<ProductReviewId, ProductReview> {

  public ProductReviews(List<ProductReview> entities) {
	super(entities);
  }

  public static ProductReviews createOf(ProductReview... productReview) {
	var entities = Arrays.asList(productReview);

	return new ProductReviews(entities);
  }
}
