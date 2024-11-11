package com.jeferro.products.products.product_reviews.domain.models;

import java.util.Locale;

import com.jeferro.products.products.product_reviews.domain.events.ProductReviewCreated;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewUpdated;
import com.jeferro.products.products.product_reviews.domain.exceptions.ProductReviewDoesNotBelongUser;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.ddd.domain.models.auth.Username;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
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

  private void setLocale(Locale locale) {
	ValueValidationUtils.isNotNull(locale, "locale", this);
	this.locale = locale;
  }

  private void setComment(String comment) {
	ValueValidationUtils.isNotNull(comment, "comment", this);
	this.comment = comment;
  }

  private void ensureProductReviewBelongsToUser(Username username) {
	if (username.equals(id.getUsername())) {
	  return;
	}

	throw ProductReviewDoesNotBelongUser.belongsToOtherUser(id, username);
  }
}
