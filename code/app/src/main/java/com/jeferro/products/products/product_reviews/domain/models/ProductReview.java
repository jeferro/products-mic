package com.jeferro.products.products.product_reviews.domain.models;

import com.jeferro.products.products.product_reviews.domain.events.ProductReviewCreated;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewUpdated;
import com.jeferro.products.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.auth.domain.models.Username;

public class ProductReview extends AggregateRoot<ProductReviewId> {

  private String comment;

  public ProductReview(ProductReviewId id, String comment) {
	super(id);

	setComment(comment);
  }

  public static ProductReview createOf(ProductCode productCode, String comment, Username username) {
	var productReviewId = ProductReviewId.createOf(username, productCode);

	var productReview = new ProductReview(productReviewId, comment);

	var event = ProductReviewCreated.create(productReview);
	productReview.record(event);

	return productReview;
  }

  public void update(String comment, Username username) {
	ensureProductReviewBelongsToUser(username);

	setComment(comment);

	this.comment = comment;

	var event = ProductReviewUpdated.create(this);
	record(event);
  }

  public void deleteByUser(Username username) {
	ensureProductReviewBelongsToUser(username);

	var event = ProductReviewDeleted.create(this);
	record(event);
  }

  public void deleteBySystem() {
	var event = ProductReviewDeleted.create(this);
	record(event);
  }

  public Username getUsername() {
	return id.getUsername();
  }

  public ProductCode getProductCode() {
	return id.getProductCode();
  }

  public String getComment() {
	return comment;
  }

  private void setComment(String comment) {
	if (comment == null) {
	  throw ValueValidationException.createOfMessage("Comment is null");
	}

	this.comment = comment;
  }

  private void ensureProductReviewBelongsToUser(Username username) {
	if (username.equals(id.getUsername())) {
	  return;
	}

	throw ForbiddenOperationInProductReviewException.belongsToOtherUser(id, username);
  }
}
