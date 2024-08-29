package com.jeferro.products.products.product_reviews.domain.exceptions;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.domain.exceptions.NotFoundException;

import static com.jeferro.products.products.product_reviews.domain.exceptions.ProductReviewsExceptionCodes.REVIEW_NOT_FOUND;

public class ProductReviewNotFoundException extends NotFoundException {

  protected ProductReviewNotFoundException(String message) {
	super(REVIEW_NOT_FOUND.value, "Product Review not found", message);
  }

  public static ProductReviewNotFoundException createOf(ProductReviewId productReviewId) {
	return new ProductReviewNotFoundException("Product review " + productReviewId + " not found");
  }
}
