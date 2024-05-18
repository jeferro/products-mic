package com.jeferro.products.product_reviews.application.commands;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.shared.application.commands.Command;
import com.jeferro.products.shared.domain.models.auth.Auth;

public class DeleteProductReviewCommand extends Command<ProductReview> {

  private final ProductReviewId productReviewId;

  public DeleteProductReviewCommand(Auth auth, ProductReviewId productReviewId) {
	super(auth);
	this.productReviewId = productReviewId;
  }

  public ProductReviewId getProductReviewId() {
	return productReviewId;
  }
}
