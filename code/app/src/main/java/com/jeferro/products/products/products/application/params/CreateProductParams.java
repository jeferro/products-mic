package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.locale.domain.models.LocalizedData;

public class CreateProductParams extends Params<Product> {

  private ProductCode code;

  private ProductTypeId typeId;

  private LocalizedData name;

  public CreateProductParams(ProductCode code,
	  ProductTypeId typeId,
	  LocalizedData name) {
	super();

	setCode(code);
	setTypeId(typeId);
	setName(name);
  }

  public ProductCode getCode() {
	return code;
  }

  private void setCode(ProductCode code) {
	if (code == null) {
	  throw ValueValidationException.createOfMessage("Product code is null");
	}

	this.code = code;
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

  public LocalizedData getName() {
	return name;
  }

  private void setName(LocalizedData name) {
	if (name == null) {
	  throw ValueValidationException.createOfMessage("Name is null");
	}

	this.name = name;
  }
}
