package com.jeferro.products.product_reviews.application.commands;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.application.commands.Command;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.users.Username;

public class CreateProductReviewCommand extends Command<ProductReview> {

  private final ProductId productId;

  private final String comment;

  public CreateProductReviewCommand(Auth auth, ProductId productId, String comment) {
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
