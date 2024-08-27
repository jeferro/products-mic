package com.jeferro.products.product_reviews.application.commands;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.commands.Command;
import com.jeferro.shared.domain.models.auth.Auth;

public class DeleteAllProductReviewsOfProductCommand extends Command<Void> {

  private final ProductId productId;

  public DeleteAllProductReviewsOfProductCommand(Auth auth, ProductId productId) {
	super(auth);
	this.productId = productId;
  }

  public ProductId getProductId() {
	return productId;
  }
}
