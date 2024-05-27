package com.jeferro.products.product_reviews.domain.exceptions;

import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.shared.domain.exceptions.ConstraintException;

public class ProductReviewAlreadyExistsException extends ConstraintException {

  protected ProductReviewAlreadyExistsException(String message) {
	super(ProductReviewsExceptionCodes.REVIEW_ALREADY_EXISTS.value, "Product Review already exists", message);
  }

  public static ProductReviewAlreadyExistsException createOf(ProductReviewId productReviewId) {
	return new ProductReviewAlreadyExistsException("Product review " + productReviewId + " already exists");
  }
}
