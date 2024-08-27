package com.jeferro.products.product_reviews.application.commands;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.application.commands.Command;
import com.jeferro.shared.domain.models.auth.Auth;

public class UpdateProductReviewCommand extends Command<ProductReview> {

  private final ProductReviewId productReviewId;

  private final String comment;

  public UpdateProductReviewCommand(Auth auth, ProductReviewId productReviewId, String comment) {
	super(auth);
	this.productReviewId = productReviewId;
	this.comment = comment;
  }

  public ProductReviewId getProductReviewId() {
	return productReviewId;
  }

  public String getComment() {
	return comment;
  }
}
