package com.jeferro.products.products.application.params;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.auth.Auth;

public class DeleteProductParams extends Params<Product> {

  private ProductId productId;

  public DeleteProductParams(Auth auth, ProductId productId) {
	super(auth);

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
