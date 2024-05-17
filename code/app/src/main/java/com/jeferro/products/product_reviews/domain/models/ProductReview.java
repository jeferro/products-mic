package com.jeferro.products.product_reviews.domain.models;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.aggregates.AggregateRoot;
import com.jeferro.products.shared.domain.models.users.Username;

public class ProductReview extends AggregateRoot<ProductReviewId> {

  private final String comment;

  public ProductReview(ProductReviewId id, String comment) {
	super(id);

	validateComment(comment);

	this.comment = comment;
  }

  public static ProductReview createOf(Username username, ProductId productId, String comment) {
	var productReviewId = ProductReviewId.createOf(username, productId);

	return new ProductReview(productReviewId, comment);
  }

  public Username getUsername() {
	return id.getUsername();
  }

  public ProductId getProductId() {
	return id.getProductId();
  }

  public String getComment() {
	return comment;
  }

  private void validateComment(String comment) {
	if (comment == null) {
	  throw ValueValidationException.createOfMessage("Comment is null");
	}
  }
}
