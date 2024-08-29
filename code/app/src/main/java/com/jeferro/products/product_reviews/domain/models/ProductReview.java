package com.jeferro.products.product_reviews.domain.models;

import com.jeferro.products.product_reviews.domain.events.ProductReviewCreated;
import com.jeferro.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.product_reviews.domain.events.ProductReviewUpdated;
import com.jeferro.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.products.domain.models.ProductCode;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.models.auth.UserAuth;
import com.jeferro.shared.domain.models.auth.Username;

public class ProductReview extends AggregateRoot<ProductReviewId> {

  private String comment;

  public ProductReview(ProductReviewId id, String comment) {
	super(id);

	setComment(comment);
  }

  public static ProductReview createOf(ProductCode productCode, String comment, Auth auth) {
	var username = auth.getUsernameOrError();

	var productReviewId = ProductReviewId.createOf(username, productCode);

	var productReview = new ProductReview(productReviewId, comment);

	var event = ProductReviewCreated.create(productReview, auth);
	productReview.record(event);

	return productReview;
  }

  public void update(String comment, Auth auth) {
	ensureProductReviewBelongsToUserAuth(auth);

	setComment(comment);

	this.comment = comment;

	var event = ProductReviewUpdated.create(this, auth);
	record(event);
  }

  public void deleteByUser(Auth auth) {
	ensureProductReviewBelongsToUserAuth(auth);

	var event = ProductReviewDeleted.create(this, auth);
	record(event);
  }

  public void deleteBySystem(Auth auth) {
	var event = ProductReviewDeleted.create(this, auth);
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

  private void ensureProductReviewBelongsToUserAuth(Auth auth) {
	if (auth instanceof UserAuth userAuth
		&& userAuth.belongsToUser(id.getUsername())) {
	  return;
	}

	throw ForbiddenOperationInProductReviewException.belongsToOtherUser(id, auth);
  }
}
