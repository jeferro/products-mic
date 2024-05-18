package com.jeferro.products.product_reviews.domain.exceptions;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.shared.domain.exceptions.NotFoundException;
import com.jeferro.products.shared.domain.models.auth.Auth;

public class ForbiddenOperationInProductReviewException extends NotFoundException {

  protected ForbiddenOperationInProductReviewException(String message) {
	super(message);
  }

  public static ForbiddenOperationInProductReviewException belongsToOtherUser(ProductReview productReview, Auth auth) {
	return new ForbiddenOperationInProductReviewException("Product review " + productReview.getId() + " don't belong to auth " + auth);
  }
}
