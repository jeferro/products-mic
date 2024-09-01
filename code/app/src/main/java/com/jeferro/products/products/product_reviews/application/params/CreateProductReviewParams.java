package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.Params;

public class CreateProductReviewParams extends Params<ProductReview> {

  private final ProductCode productCode;

  private final String comment;

  public CreateProductReviewParams(ProductCode productCode, String comment) {
	super();
	this.productCode = productCode;
	this.comment = comment;
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  public String getComment() {
	return comment;
  }
}
