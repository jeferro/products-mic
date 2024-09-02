package com.jeferro.products.products.products.domain.models.product_types;

import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;
import org.apache.commons.lang3.StringUtils;

public class ProductType extends ValueObject {

  private ProductTypeId id;

  private String name;

  public ProductType(
	  ProductTypeId id,
	  String name) {
	setId(id);
	setName(name);
  }

  public static ProductType createOf(ProductTypeId id, String name) {
	return new ProductType(id, name);
  }

  public ProductTypeId getId() {
	return id;
  }

  public boolean hasId(ProductTypeId id) {
	return this.id.equals(id);
  }

  public void setId(ProductTypeId id) {
	if (id == null) {
	  throw ValueValidationException.createOfMessage("Id is null");
	}

	this.id = id;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	if (StringUtils.isBlank(name)) {
	  throw ValueValidationException.createOfMessage("Name is blank");
	}

	this.name = name;
  }
}
