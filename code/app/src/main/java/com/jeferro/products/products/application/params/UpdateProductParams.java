package com.jeferro.products.products.application.params;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductCode;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import org.apache.commons.lang3.StringUtils;

public class UpdateProductParams extends Params<Product> {

  private ProductCode productCode;

  private String name;

  public UpdateProductParams(ProductCode productCode, String name) {
	super();

	setProductCode(productCode);
	setName(name);
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  public String getName() {
	return name;
  }

  private void setProductCode(ProductCode productCode) {
	if (productCode == null) {
	  throw ValueValidationException.createOfMessage("Product code is null");
	}

	this.productCode = productCode;
  }

  private void setName(String name) {
	if (StringUtils.isBlank(name)) {
	  throw ValueValidationException.createOfMessage("Name is blank");
	}

	this.name = name;
  }
}
