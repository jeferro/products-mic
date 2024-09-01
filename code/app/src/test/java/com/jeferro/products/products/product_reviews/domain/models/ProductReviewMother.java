package com.jeferro.products.products.product_reviews.domain.models;

import com.jeferro.products.products.products.domain.models.ProductCodeMother;
import com.jeferro.products.shared.domain.models.auth.AuthMother;

public abstract class ProductReviewMother {

  public static ProductReview userReviewOfApple() {
	var appleCode = ProductCodeMother.appleCode();
	var userAuth = AuthMother.user();

	var productReviewId = ProductReviewId.createOf(userAuth.getUsername(), appleCode);

	return new ProductReview(productReviewId, "Comment about apple");
  }

	public static ProductReview adminReviewOfApple() {
	  var appleCode = ProductCodeMother.appleCode();
	  var adminAuth = AuthMother.admin();

	  var productReviewId = ProductReviewId.createOf(adminAuth.getUsername(), appleCode);

	  return new ProductReview(productReviewId, "I love apples");
	}
}