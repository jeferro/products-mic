package com.jeferro.products.products.application.params;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;

public class GetProductParams extends Params<Product> {

  private ProductId productId;

  public GetProductParams(ProductId productId) {
	super();

	setProductId(productId);
  }

  public ProductId getProductId() {
	return productId;
  }

  private void setProductId(ProductId productId) {
	if (productId == null) {
	  throw ValueValidationException.createOfMessage("Product identifier is null");
	}

	this.productId = productId;
  }
}
