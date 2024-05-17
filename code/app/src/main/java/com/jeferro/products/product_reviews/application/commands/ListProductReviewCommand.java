package com.jeferro.products.product_reviews.application.commands;

import com.jeferro.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.application.commands.Command;
import com.jeferro.products.shared.domain.models.auth.Auth;

public class ListProductReviewCommand extends Command<ProductReviews> {

  private final ProductId productId;

  public ListProductReviewCommand(Auth auth, ProductId productId) {
	super(auth);
	this.productId = productId;
  }

  public ProductId getProductId() {
	return productId;
  }
}
