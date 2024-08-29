package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.application.Params;

public class UpdateProductReviewParams extends Params<ProductReview> {

  private final ProductReviewId productReviewId;

  private final String comment;

  public UpdateProductReviewParams(ProductReviewId productReviewId, String comment) {
	super();
	this.productReviewId = productReviewId;
	this.comment = comment;
  }

  public ProductReviewId getProductReviewId() {
	return productReviewId;
  }

  public String getComment() {
	return comment;
  }
}
