package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.application.params.Params;
import lombok.Getter;

@Getter
public class GetProductReviewParams extends Params<ProductReview> {

  private final ProductReviewId productReviewId;

  public GetProductReviewParams(ProductReviewId productReviewId) {
	super();
	this.productReviewId = productReviewId;
  }

}
