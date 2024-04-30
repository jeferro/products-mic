package com.jeferro.products.products.domain.events;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.domain.events.Event;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;

import java.time.Instant;

public abstract class ProductEvent extends Event {

    private final ProductId productId;

    protected ProductEvent(ProductId productId, String occurredBy, Instant occurredOn) {
        super(occurredBy, occurredOn);

		validateProductId(productId);

		this.productId = productId;
    }

	public ProductId getProductId() {
        return productId;
    }

	private static void validateProductId(ProductId productId) {
		if (productId == null) {
			throw ValueValidationException.createOfMessage("Product identifier is null");
		}
	}
}
