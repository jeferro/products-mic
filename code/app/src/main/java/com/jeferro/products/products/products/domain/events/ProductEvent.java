package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.events.Event;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public abstract class ProductEvent extends Event {

  private ProductCode code;

  protected ProductEvent(EventId id,
	  ProductCode code) {
	super(id);

	setCode(code);
  }

  private void setCode(ProductCode code) {
	ValueValidationUtils.isNotNull(code, "code", this);
	this.code = code;
  }
}
