package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.models.locale.LocalizedData;

public class ProductCreated extends ProductEvent {

  private ProductCreated(EventId id,
	  ProductCode code,
	  LocalizedData name,
	  ProductStatus status) {
	super(id, code, name, status);
  }

  public static ProductCreated create(Product product) {
	var id = EventId.create();

	var code = product.getCode();
	var name = product.getName();
	var status = product.getStatus();

	return new ProductCreated(id, code, name, status);
  }
}
