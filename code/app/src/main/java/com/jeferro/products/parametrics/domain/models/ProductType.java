package com.jeferro.products.parametrics.domain.models;

import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
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

  public boolean hasId(ProductTypeId id) {
	return this.id.equals(id);
  }

  public void setId(ProductTypeId id) {
	ValueValidationUtils.isNotNull(id, "Id");
	this.id = id;
  }

  public void setName(String name) {
	ValueValidationUtils.isNotBlank(name, "Name");
	this.name = name;
  }
}
