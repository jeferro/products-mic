package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.application.params.Params;

public class DeleteProductReviewParams extends Params<ProductReview> {

  private final ProductReviewId productReviewId;

  public DeleteProductReviewParams(ProductReviewId productReviewId) {
	super();
	this.productReviewId = productReviewId;
  }

  public ProductReviewId getProductReviewId() {
	return productReviewId;
  }
}
