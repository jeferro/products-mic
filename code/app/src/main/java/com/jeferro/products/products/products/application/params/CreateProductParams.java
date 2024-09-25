package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.locale.domain.models.LocalizedField;

public class CreateProductParams extends Params<Product> {

  private ProductCode productCode;

  private ProductTypeId typeId;

  private LocalizedField name;

  public CreateProductParams(ProductCode productCode,
	  ProductTypeId typeId,
	  LocalizedField name) {
	super();

	setProductCode(productCode);
	setTypeId(typeId);
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

  public ProductTypeId getTypeId() {
	return typeId;
  }

  public void setTypeId(ProductTypeId typeId) {
	if (typeId == null) {
	  throw ValueValidationException.createOfMessage("Type id is null");
	}

	this.typeId = typeId;
  }

  public LocalizedField getName() {
	return name;
  }

  private void setName(LocalizedField name) {
	if (name == null) {
	  throw ValueValidationException.createOfMessage("Name is null");
	}

	this.name = name;
  }
}
