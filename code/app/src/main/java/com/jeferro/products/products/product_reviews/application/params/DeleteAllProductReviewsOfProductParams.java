package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.params.Params;
import lombok.Getter;

@Getter
public class DeleteAllProductReviewsOfProductParams extends Params<Void> {

  private final ProductCode productCode;

  public DeleteAllProductReviewsOfProductParams(ProductCode productCode) {
	super();
	this.productCode = productCode;
  }

}
