package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.events.Event;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;

public abstract class ProductEvent extends Event {

  private ProductCode code;

  protected ProductEvent(EventId id,
	  ProductCode code) {
	super(id);

	setCode(code);
  }

  public ProductCode getCode() {
	return code;
  }

  private void setCode(ProductCode code) {
	if (code == null) {
	  throw ValueValidationException.createOfMessage("Code is null");
	}

	this.code = code;
  }
}
