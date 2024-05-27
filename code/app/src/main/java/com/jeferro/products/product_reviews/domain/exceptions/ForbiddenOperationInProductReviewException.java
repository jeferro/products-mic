package com.jeferro.products.product_reviews.domain.exceptions;

import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.shared.domain.exceptions.NotFoundException;
import com.jeferro.products.shared.domain.models.auth.Auth;

public class ForbiddenOperationInProductReviewException extends NotFoundException {

  protected ForbiddenOperationInProductReviewException(String message) {
	super(ProductReviewsExceptionCodes.FORBIDDEN_REVIEW_OPERATION.value, "Forbidden Operation", message);
  }

  public static ForbiddenOperationInProductReviewException belongsToOtherUser(ProductReviewId productReviewId, Auth auth) {
	return new ForbiddenOperationInProductReviewException("Product review " + productReviewId + " don't belong to auth " + auth);
  }
}
