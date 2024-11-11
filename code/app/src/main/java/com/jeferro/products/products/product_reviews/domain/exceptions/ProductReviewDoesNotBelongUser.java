package com.jeferro.products.products.product_reviews.domain.exceptions;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.domain.models.auth.Username;
import com.jeferro.shared.ddd.domain.exceptions.auth.ForbiddenException;

public class ProductReviewDoesNotBelongUser extends ForbiddenException {

  protected ProductReviewDoesNotBelongUser(String message) {
	super(message);
  }

  public static ProductReviewDoesNotBelongUser belongsToOtherUser(ProductReviewId productReviewId, Username username) {
	return new ProductReviewDoesNotBelongUser("Product review " + productReviewId + " don't belong to user " + username);
  }
}
