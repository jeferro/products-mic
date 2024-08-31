package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.domain.events.Event;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.locale.LocalizedData;

public abstract class ProductEvent extends Event {

  private ProductCode code;

  private LocalizedData name;

  private ProductStatus status;

  protected ProductEvent(EventId id,
	  ProductCode code,
	  LocalizedData name,
	  ProductStatus status) {
	super(id);

	setCode(code);
	setName(name);
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

  public LocalizedData getName() {
	return name;
  }

  public void setName(LocalizedData name) {
	if (name == null) {
	  throw ValueValidationException.createOfMessage("Name is null");
	}

	this.name = name;
  }
}
