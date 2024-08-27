package com.jeferro.products.products.application.params;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;

public class DeleteProductParams extends Params<Product> {

  private ProductId productId;

  public DeleteProductParams(ProductId productId) {
	super();

	setValidateProductId(productId);
  }

  public ProductId getProductId() {
	return productId;
  }

  private void setValidateProductId(ProductId productId) {
	if (productId == null) {
	  throw ValueValidationException.createOfMessage("Product identifier is null");
	}

	this.productId = productId;
  }
}
