package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.ddd.application.Params;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;

public class UpdateProductStatusParams extends Params<Product> {

  private ProductCode productCode;

  private ProductStatus status;

  public UpdateProductStatusParams(ProductCode productCode, ProductStatus status) {
	super();

	setProductCode(productCode);
	setStatus(status);
  }

  public ProductCode getProductCode() {
	return productCode;
  }

  public ProductStatus getStatus() {
	return status;
  }

  private void setProductCode(ProductCode productCode) {
	if (productCode == null) {
	  throw ValueValidationException.createOfMessage("Product code is null");
	}

	this.productCode = productCode;
  }

  private void setStatus(ProductStatus status) {
	if (status == null) {
	  throw ValueValidationException.createOfMessage("Status is null");
	}

	this.status = status;
  }
}
