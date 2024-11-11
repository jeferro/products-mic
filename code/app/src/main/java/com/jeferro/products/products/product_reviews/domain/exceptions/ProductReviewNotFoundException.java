package com.jeferro.products.products.product_reviews.domain.exceptions;

import static com.jeferro.products.shared.domain.exceptions.ProductExceptionCodes.REVIEW_NOT_FOUND;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.domain.exceptions.NotFoundException;

public class ProductReviewNotFoundException extends NotFoundException {

  protected ProductReviewNotFoundException(String message) {
	super(REVIEW_NOT_FOUND, "Product Review not found", message);
  }

  public static ProductReviewNotFoundException createOf(ProductReviewId productReviewId) {
	return new ProductReviewNotFoundException("Product review " + productReviewId + " not found");
  }
}
