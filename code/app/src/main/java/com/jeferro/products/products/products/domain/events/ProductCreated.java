package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
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

  public void setTypeId(ProductTypeId typeId) {
	ValueValidationUtils.isNotNull(typeId, "Type id");
	this.typeId = typeId;
  }

  public void setName(LocalizedField name) {
	ValueValidationUtils.isNotNull(name, "Name");
	this.name = name;
  }

  public void setStatus(ProductStatus status) {
	ValueValidationUtils.isNotNull(status, "Status");
	this.status = status;
  }
}
