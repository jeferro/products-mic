package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class ProductUpdated extends ProductEvent {

  private LocalizedField name;

  private ProductStatus status;

  private ProductUpdated(EventId id,
	  ProductCode code,
	  LocalizedField name,
	  ProductStatus status) {
	super(id, code);

	setName(name);
	setStatus(status);
  }

  public static ProductUpdated create(Product product) {
	var id = EventId.create();

	var code = product.getCode();
	var name = product.getName();
	var status = product.getStatus();

	return new ProductUpdated(id, code, name, status);
  }

  public void setStatus(ProductStatus status) {
	ValueValidationUtils.isNotNull(status, "Status");
	this.status = status;
  }

  public void setName(LocalizedField name) {
	ValueValidationUtils.isNotNull(name, "Name");
	this.name = name;
  }
}
