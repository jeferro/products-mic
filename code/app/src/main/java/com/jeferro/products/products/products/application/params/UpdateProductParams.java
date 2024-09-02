package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.locale.domain.models.LocalizedField;

public class UpdateProductParams extends Params<Product> {

  private ProductCode productCode;

  private LocalizedField name;

  public UpdateProductParams(ProductCode productCode, LocalizedField name) {
	super();

	setProductCode(productCode);
	setName(name);
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  public LocalizedField getName() {
	return name;
  }

  private void setProductCode(ProductCode productCode) {
	if (productCode == null) {
	  throw ValueValidationException.createOfMessage("Product code is null");
	}

	this.productCode = productCode;
  }

  private void setName(LocalizedField name) {
	if (name == null) {
	  throw ValueValidationException.createOfMessage("Name is null");
	}

	this.name = name;
  }
}
