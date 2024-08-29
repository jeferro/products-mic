package com.jeferro.products.products.domain.events;

import java.time.Instant;

import com.jeferro.products.products.domain.models.ProductCode;
import com.jeferro.shared.domain.events.Event;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;

public abstract class ProductEvent extends Event {

  private ProductCode productCode;

  protected ProductEvent(EventId id, ProductCode productCode, String occurredBy, Instant occurredOn) {
	super(id, occurredBy, occurredOn);

	setProductCode(productCode);
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
}
