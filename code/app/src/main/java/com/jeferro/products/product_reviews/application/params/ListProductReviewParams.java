package com.jeferro.products.product_reviews.application.params;

import com.jeferro.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;

public class ListProductReviewParams extends Params<ProductReviews> {

  private final ProductId productId;

  public ListProductReviewParams(ProductId productId) {
	super();
	this.productId = productId;
  }

  public ProductId getProductId() {
	return productId;
  }
}
