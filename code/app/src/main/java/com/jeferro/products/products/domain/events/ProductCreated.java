package com.jeferro.products.products.domain.events;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.services.time.TimeService;

import java.time.Instant;

public class ProductCreated extends ProductEvent {

    private ProductCreated(ProductId productId, String occurredBy, Instant occurredOn) {
        super(productId, occurredBy, occurredOn);
    }

    public static ProductCreated create(Product product, Auth auth) {
        var productId = product.getId();

		var occurredBy = auth.who();
		var occurredOn = TimeService.now();

        return new ProductCreated(productId, occurredBy, occurredOn);
    }
}
