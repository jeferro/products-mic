package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.domain.events.Event;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;

public abstract class ProductEvent extends Event {

  private ProductCode code;

  private ProductStatus status;

  protected ProductEvent(EventId id,
	  ProductCode code,
	  ProductStatus status) {
	super(id);

	setCode(code);
	setStatus(status);
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
