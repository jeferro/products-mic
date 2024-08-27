package com.jeferro.products.product_reviews.application.params;

import com.jeferro.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.models.auth.Auth;

public class ListProductReviewParams extends Params<ProductReviews> {

  private final ProductId productId;

  public ListProductReviewParams(Auth auth, ProductId productId) {
	super(auth);
	this.productId = productId;
  }

  public ProductId getProductId() {
	return productId;
  }
}
