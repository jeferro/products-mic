package com.jeferro.products.products.domain.events;

import java.time.Instant;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.domain.events.Event;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.products.shared.domain.exceptions.internals.ValueValidationException;

public abstract class ProductEvent extends Event {

  private ProductId productId;

  protected ProductEvent(EventId id, ProductId productId, String occurredBy, Instant occurredOn) {
	super(id, occurredBy, occurredOn);

	setProductId(productId);
  }

  public ProductId getProductId() {
	return productId;
  }

  private void setProductId(ProductId productId) {
	if (productId == null) {
	  throw ValueValidationException.createOfMessage("Product identifier is null");
	}

	this.productId = productId;
  }
}
