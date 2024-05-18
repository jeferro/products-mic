package com.jeferro.products.product_reviews.domain.models;

import com.jeferro.products.products.domain.models.ProductIdMother;
import com.jeferro.products.shared.domain.models.auth.AuthMother;

public abstract class ProductReviewMother {

  public static ProductReview userReviewOfApple() {
	var appleId = ProductIdMother.appleId();
	var userAuth = AuthMother.user();

	var productReviewId = ProductReviewId.createOf(userAuth.getUsername(), appleId);

	return new ProductReview(productReviewId, "Comment about apple");
  }

	public static ProductReview otherUserReviewOfApple() {
	  var appleId = ProductIdMother.appleId();
	  var otherUserAuth = AuthMother.otherUser();

	  var productReviewId = ProductReviewId.createOf(otherUserAuth.getUsername(), appleId);

	  return new ProductReview(productReviewId, "Comment about apple");
	}
}