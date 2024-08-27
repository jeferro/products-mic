package com.jeferro.products.product_reviews.application.params;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.models.auth.Auth;

public class GetProductReviewParams extends Params<ProductReview> {

  private final ProductReviewId productReviewId;

  public GetProductReviewParams(Auth auth, ProductReviewId productReviewId) {
	super(auth);
	this.productReviewId = productReviewId;
  }

  public ProductReviewId getProductReviewId() {
	return productReviewId;
  }
}
