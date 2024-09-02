package com.jeferro.products.products.product_reviews.domain.exceptions;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.auth.domain.models.Username;
import com.jeferro.shared.ddd.domain.exceptions.ForbiddenException;

public class ForbiddenOperationInProductReviewException extends ForbiddenException {

  protected ForbiddenOperationInProductReviewException(String message) {
	super(message);
  }

  public static ForbiddenOperationInProductReviewException belongsToOtherUser(ProductReviewId productReviewId, Username username) {
	return new ForbiddenOperationInProductReviewException("Product review " + productReviewId + " don't belong to user " + username);
  }
}
