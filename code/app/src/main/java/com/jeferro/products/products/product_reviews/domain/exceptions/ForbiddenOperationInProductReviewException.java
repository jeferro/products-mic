package com.jeferro.products.products.product_reviews.domain.exceptions;

import static com.jeferro.products.products.product_reviews.domain.exceptions.ProductReviewsExceptionCodes.FORBIDDEN_REVIEW_OPERATION;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.domain.exceptions.NotFoundException;
import com.jeferro.shared.domain.models.auth.Username;

public class ForbiddenOperationInProductReviewException extends NotFoundException {

  protected ForbiddenOperationInProductReviewException(String message) {
	super(FORBIDDEN_REVIEW_OPERATION.value, "Forbidden Operation", message);
  }

  public static ForbiddenOperationInProductReviewException belongsToOtherUser(ProductReviewId productReviewId, Username username) {
	return new ForbiddenOperationInProductReviewException("Product review " + productReviewId + " don't belong to user " + username);
  }
}
