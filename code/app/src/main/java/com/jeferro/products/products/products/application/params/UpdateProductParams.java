package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.locale.LocalizedData;

public class UpdateProductParams extends Params<Product> {

  private ProductCode productCode;

  private LocalizedData name;

  public UpdateProductParams(ProductCode productCode, LocalizedData name) {
	super();

	setProductCode(productCode);
	setName(name);
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  public LocalizedData getName() {
	return name;
  }

  private void setProductCode(ProductCode productCode) {
	if (productCode == null) {
	  throw ValueValidationException.createOfMessage("Product code is null");
	}

	this.productCode = productCode;
  }

  private void setName(LocalizedData name) {
	if (name == null) {
	  throw ValueValidationException.createOfMessage("Name is null");
	}

	this.name = name;
  }
}
