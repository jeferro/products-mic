package com.jeferro.products.product_reviews.domain.exceptions;

import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.domain.exceptions.ConstraintException;

import static com.jeferro.products.product_reviews.domain.exceptions.ProductReviewsExceptionCodes.REVIEW_ALREADY_EXISTS;

public class ProductReviewAlreadyExistsException extends ConstraintException {

  protected ProductReviewAlreadyExistsException(String message) {
	super(REVIEW_ALREADY_EXISTS.value, "Product Review already exists", message);
  }

  public static ProductReviewAlreadyExistsException createOf(ProductReviewId productReviewId) {
	return new ProductReviewAlreadyExistsException("Product review " + productReviewId + " already exists");
  }
}
