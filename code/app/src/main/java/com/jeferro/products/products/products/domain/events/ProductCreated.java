package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.services.time.TimeService;

import java.time.Instant;

public class ProductCreated extends ProductEvent {

    private ProductCreated(EventId id, ProductCode productCode, String occurredBy, Instant occurredOn) {
        super(id, productCode, occurredBy, occurredOn);
    }

    public static ProductCreated create(Product product, Auth auth) {
        var productCode = product.getCode();

		var id = EventId.create();
		var occurredBy = auth.who();
		var occurredOn = TimeService.now();

        return new ProductCreated(id, productCode, occurredBy, occurredOn);
    }
}
