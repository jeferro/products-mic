package com.jeferro.products.products.product_reviews.domain.models;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.ddd.domain.models.aggregates.Identifier;
import com.jeferro.shared.auth.domain.models.Username;

public class ProductReviewId extends Identifier<String> {

  private Username username;

  private ProductCode productCode;

  public ProductReviewId(String value) {
	super(value);

	String[] slices = value.split(SEPARATOR);

	if (slices.length != 2) {
	  throw ValueValidationException.createOfMessage("Wrong format of identifier: " + value);
	}

	this.username = new Username(slices[0]);
	this.productCode = new ProductCode(slices[1]);
  }

  private ProductReviewId(Username username, ProductCode productCode) {
	super(username + SEPARATOR + productCode);

	setUsername(username);
	setProductCode(productCode);
  }

  public static ProductReviewId createOf(Username username, ProductCode productCode) {
	return new ProductReviewId(username, productCode);
  }

  public Username getUsername() {
	return username;
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  private void setUsername(Username username) {
	if (username == null) {
	  throw ValueValidationException.createOfMessage("Username is null");
	}

	this.username = username;
  }

  private void setProductCode(ProductCode productCode) {
	if (productCode == null) {
	  throw ValueValidationException.createOfMessage("Product code is null");
	}

	this.productCode = productCode;
  }
}
