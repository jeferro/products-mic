package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import org.apache.commons.lang3.StringUtils;

public class CreateProductParams extends Params<Product> {

  private ProductCode productCode;

  private String name;

  public CreateProductParams(ProductCode productCode, String name) {
	super();

	setProductCode(productCode);
	setName(name);
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

  public String getName() {
	return name;
  }

  private void setName(String name) {
	if (StringUtils.isBlank(name)) {
	  throw ValueValidationException.createOfMessage("Name is blank");
	}

	this.name = name;
  }
}
