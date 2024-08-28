package com.jeferro.products.product_reviews.application.params;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;

public class DeleteAllProductReviewsOfProductParams extends Params<Void> {

  private final ProductId productId;

  public DeleteAllProductReviewsOfProductParams(ProductId productId) {
	super();
	this.productId = productId;
  }

  public ProductId getProductId() {
	return productId;
  }
}
