package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;

public class DeleteProductParams extends Params<Product> {

  private ProductCode productCode;

  public DeleteProductParams(ProductCode productCode) {
	super();

	setValidateProductCode(productCode);
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  private void setValidateProductCode(ProductCode productCode) {
	if (productCode == null) {
	  throw ValueValidationException.createOfMessage("Product code is null");
	}

	this.productCode = productCode;
  }
}
