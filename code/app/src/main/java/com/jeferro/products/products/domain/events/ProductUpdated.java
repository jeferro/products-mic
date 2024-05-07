package com.jeferro.products.products.domain.events;

import java.time.Instant;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.domain.events.EventId;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.services.time.TimeService;

public class ProductUpdated extends ProductEvent {

	private ProductUpdated(EventId id, ProductId productId, String occurredBy, Instant occurredOn) {
		super(id, productId, occurredBy, occurredOn);
	}

	public static ProductUpdated create(Product product, Auth auth) {
		var productId = product.getId();

		var id = EventId.create();
		var occurredBy = auth.who();
		var occurredOn = TimeService.now();

		return new ProductUpdated(id, productId, occurredBy, occurredOn);
	}
}
