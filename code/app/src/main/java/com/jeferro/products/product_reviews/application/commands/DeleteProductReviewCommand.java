package com.jeferro.products.product_reviews.application.commands;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.application.commands.Command;
import com.jeferro.products.shared.domain.models.auth.Auth;

public class DeleteProductReviewCommand extends Command<ProductReview> {

  private final ProductId productId;

  private final ProductReviewId productReviewId;

  public DeleteProductReviewCommand(Auth auth, ProductId productId, ProductReviewId productReviewId) {
	super(auth);
	this.productId = productId;
	this.productReviewId = productReviewId;
  }

  public ProductId getProductId() {
	return productId;
  }

  public ProductReviewId getProductReviewId() {
	return productReviewId;
  }
}
