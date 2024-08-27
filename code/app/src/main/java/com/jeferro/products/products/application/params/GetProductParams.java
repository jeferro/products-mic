package com.jeferro.products.products.application.params;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.auth.Auth;

public class GetProductParams extends Params<Product> {

  private ProductId productId;

  public GetProductParams(Auth auth, ProductId productId) {
	super(auth);

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
