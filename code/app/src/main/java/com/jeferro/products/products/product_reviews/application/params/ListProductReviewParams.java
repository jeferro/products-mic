package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.params.Params;
import lombok.Getter;

@Getter
public class ListProductReviewParams extends Params<ProductReviews> {

  private final ProductCode productCode;

  public ListProductReviewParams(ProductCode productCode) {
	super();
	this.productCode = productCode;
  }

}
