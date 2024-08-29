package com.jeferro.products.product_reviews.domain.models;

import com.jeferro.products.products.domain.models.ProductCodeMother;
import com.jeferro.products.shared.domain.models.auth.AuthMother;

public abstract class ProductReviewMother {

  public static ProductReview userReviewOfApple() {
	var appleId = ProductCodeMother.appleId();
	var userAuth = AuthMother.user();

	var productReviewId = ProductReviewId.createOf(userAuth.getUsername(), appleId);

	return new ProductReview(productReviewId, "Comment about apple");
  }

	public static ProductReview adminReviewOfApple() {
	  var appleId = ProductCodeMother.appleId();
	  var adminAuth = AuthMother.admin();

	  var productReviewId = ProductReviewId.createOf(adminAuth.getUsername(), appleId);

	  return new ProductReview(productReviewId, "I love apples");
	}
}