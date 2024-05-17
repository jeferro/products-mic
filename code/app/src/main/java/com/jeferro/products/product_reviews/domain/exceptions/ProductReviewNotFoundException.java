package com.jeferro.products.product_reviews.domain.exceptions;

import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.shared.domain.exceptions.ConstraintException;
import com.jeferro.products.shared.domain.exceptions.NotFoundException;

public class ProductReviewNotFoundException extends NotFoundException {

  protected ProductReviewNotFoundException(String message) {
	super(message);
  }

  public static ProductReviewNotFoundException createOf(ProductReviewId productReviewId) {
	return new ProductReviewNotFoundException("Product review " + productReviewId + " not found");
  }
}
