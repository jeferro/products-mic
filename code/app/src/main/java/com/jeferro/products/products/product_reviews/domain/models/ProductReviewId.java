package com.jeferro.products.products.product_reviews.domain.models;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.models.aggregates.Identifier;
import com.jeferro.shared.ddd.domain.models.auth.Username;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class ProductReviewId extends Identifier {

  private Username username;

  private ProductCode productCode;

  public ProductReviewId(Username username, ProductCode productCode) {
	setUsername(username);
	setProductCode(productCode);
  }

  public static ProductReviewId createOf(Username username, ProductCode productCode) {
	return new ProductReviewId(username, productCode);
  }

  private void setUsername(Username username) {
	ValueValidationUtils.isNotNull(username, "username", this);
	this.username = username;
  }

  private void setProductCode(ProductCode productCode) {
	ValueValidationUtils.isNotNull(productCode, "productCode", this);
	this.productCode = productCode;
  }
}
