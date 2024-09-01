package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.Params;

public class DeleteAllProductReviewsOfProductParams extends Params<Void> {

  private final ProductCode productCode;

  public DeleteAllProductReviewsOfProductParams(ProductCode productCode) {
	super();
	this.productCode = productCode;
  }

  public ProductCode getProductCode() {
	return productCode;
  }
}
