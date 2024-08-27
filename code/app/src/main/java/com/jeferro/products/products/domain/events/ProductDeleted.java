package com.jeferro.products.products.domain.events;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.services.time.TimeService;

import java.time.Instant;

public class ProductDeleted extends ProductEvent {

    private ProductDeleted(EventId id, ProductId productId, String occurredBy, Instant occurredOn) {
        super(id, productId, occurredBy, occurredOn);
    }

    public static ProductDeleted create(Product product, Auth auth) {
        var productId = product.getId();

		var id = EventId.create();
        var occurredBy = auth.who();
        var occurredOn = TimeService.now();

        return new ProductDeleted(id, productId, occurredBy, occurredOn);
    }
}
