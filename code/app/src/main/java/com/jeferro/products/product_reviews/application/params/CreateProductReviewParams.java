package com.jeferro.products.product_reviews.application.params;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.models.auth.Auth;

public class CreateProductReviewParams extends Params<ProductReview> {

  private final ProductId productId;

  private final String comment;

  public CreateProductReviewParams(Auth auth, ProductId productId, String comment) {
	super(auth);
	this.productId = productId;
	this.comment = comment;
  }

  public ProductId getProductId() {
	return productId;
  }

  public String getComment() {
	return comment;
  }
}
