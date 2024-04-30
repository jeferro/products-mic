package com.jeferro.products.products.domain.events;

import java.time.Instant;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.services.time.TimeService;

public class ProductUpdated extends ProductEvent {

	private ProductUpdated(ProductId productId, String occurredBy, Instant occurredOn) {
		super(productId, occurredBy, occurredOn);
	}

	public static ProductUpdated create(Product product, Auth auth) {
		var productId = product.getId();

		var occurredBy = auth.who();
		var occurredOn = TimeService.now();

		return new ProductUpdated(productId, occurredBy, occurredOn);
	}
}
