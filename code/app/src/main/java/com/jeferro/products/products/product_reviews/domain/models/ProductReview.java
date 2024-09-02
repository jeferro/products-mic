package com.jeferro.products.products.product_reviews.domain.models;

import java.util.Locale;

import com.jeferro.products.products.product_reviews.domain.events.ProductReviewCreated;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewUpdated;
import com.jeferro.products.products.product_reviews.domain.exceptions.ForbiddenOperationInProductReviewException;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.auth.domain.models.Username;

public class ProductReview extends AggregateRoot<ProductReviewId> {

  private Locale locale;

  private String comment;

  public ProductReview(ProductReviewId id,
	  Locale locale,
	  String comment) {
	super(id);

	setLocale(locale);
	setComment(comment);
  }

  public static ProductReview createOf(ProductCode productCode,
	  Username username,
	  Locale locale,
	  String comment) {
	var productReviewId = ProductReviewId.createOf(username, productCode);

	var productReview = new ProductReview(productReviewId, locale, comment);

	var event = ProductReviewCreated.create(productReview);
	productReview.record(event);

	return productReview;
  }

  public void update(String comment, Locale locale, Username username) {
	ensureProductReviewBelongsToUser(username);

	setLocale(locale);
	setComment(comment);

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

  public Locale getLocale() {
	return locale;
  }

  private void setLocale(Locale locale) {
	if (locale == null) {
	  throw ValueValidationException.createOfMessage("Locale is null");
	}

	this.locale = locale;
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
