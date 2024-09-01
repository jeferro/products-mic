package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.Params;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;

public class GetProductParams extends Params<Product> {

  private ProductCode productCode;

  public GetProductParams(ProductCode productCode) {
	super();

	setProductCode(productCode);
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  private void setProductCode(ProductCode productCode) {
	if (productCode == null) {
	  throw ValueValidationException.createOfMessage("Product code is null");
	}

	this.productCode = productCode;
  }
}
