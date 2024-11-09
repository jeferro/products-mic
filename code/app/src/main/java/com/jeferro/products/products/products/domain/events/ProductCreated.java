package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.parametrics.domain.models.ParametricValueId;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.products.parametrics.domain.models.ParametricId;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class ProductCreated extends ProductEvent {

  private LocalizedField name;

  private ParametricValueId typeId;

  private ProductStatus status;

  private ProductCreated(EventId id,
	  ProductCode code,
	  LocalizedField name,
	  ParametricValueId typeId,
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

	return new ProductCreated(id, code, name, typeId, status);
  }

  public void setTypeId(ParametricValueId typeId) {
	ValueValidationUtils.isNotNull(typeId, "typeId", this);
	this.typeId = typeId;
  }

  public void setName(LocalizedField name) {
	ValueValidationUtils.isNotNull(name, "name", this);
	this.name = name;
  }

  public void setStatus(ProductStatus status) {
	ValueValidationUtils.isNotNull(status, "status", this);
	this.status = status;
  }
}
