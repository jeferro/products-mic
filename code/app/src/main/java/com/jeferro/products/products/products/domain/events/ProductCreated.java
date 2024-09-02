package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.locale.domain.models.LocalizedField;

public class ProductCreated extends ProductEvent {

  private ProductTypeId typeId;

  private LocalizedField name;

  private ProductStatus status;

  private ProductCreated(EventId id,
	  ProductCode code,
	  ProductTypeId typeId,
	  LocalizedField name,
	  ProductStatus status) {
	super(id, code);

	setTypeId(typeId);
	setName(name);
	setStatus(status);
  }

  public static ProductCreated create(Product product) {
	var id = EventId.create();

	var code = product.getCode();
	var typeId = product.getTypeId();
	var name = product.getName();
	var status = product.getStatus();

	return new ProductCreated(id, code, typeId, name, status);
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

  public void setName(LocalizedField name) {
	if (name == null) {
	  throw ValueValidationException.createOfMessage("Name is null");
	}

	this.name = name;
  }

  public ProductStatus getStatus() {
	return status;
  }

  public void setStatus(ProductStatus status) {
	if (status == null) {
	  throw ValueValidationException.createOfMessage("Status is null");
	}

	this.status = status;
  }
}
