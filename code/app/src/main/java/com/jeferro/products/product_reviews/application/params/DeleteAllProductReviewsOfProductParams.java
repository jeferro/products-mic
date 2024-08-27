package com.jeferro.products.product_reviews.application.params;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.models.auth.Auth;

public class DeleteAllProductReviewsOfProductParams extends Params<Void> {

  private final ProductId productId;

  public DeleteAllProductReviewsOfProductParams(Auth auth, ProductId productId) {
	super(auth);
	this.productId = productId;
  }

  public ProductId getProductId() {
	return productId;
  }
}
