package com.jeferro.products.product_reviews.domain.models;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.products.shared.domain.models.aggregates.Identifier;
import com.jeferro.products.shared.domain.models.users.Username;

public class ProductReviewId extends Identifier<String> {

  private Username username;

  private ProductId productId;

  public ProductReviewId(String value) {
	super(value);

	String[] slices = value.split(SEPARATOR);

	if (slices.length != 2) {
	  throw ValueValidationException.createOfMessage("Wrong format of identifier: " + value);
	}

	this.username = new Username(slices[0]);
	this.productId = new ProductId(slices[1]);
  }

  private ProductReviewId(Username username, ProductId productId) {
	super(username + SEPARATOR + productId);

	setUsername(username);
	setProductId(productId);
  }

  public static ProductReviewId createOf(Username username, ProductId productId) {
	return new ProductReviewId(username, productId);
  }

  public Username getUsername() {
	return username;
  }

  public ProductId getProductId() {
	return productId;
  }

  private void setUsername(Username username) {
	if (username == null) {
	  throw ValueValidationException.createOfMessage("Username is null");
	}

	this.username = username;
  }

  private void setProductId(ProductId productId) {
	if (productId == null) {
	  throw ValueValidationException.createOfMessage("Product identifier is null");
	}

	this.productId = productId;
  }
}
