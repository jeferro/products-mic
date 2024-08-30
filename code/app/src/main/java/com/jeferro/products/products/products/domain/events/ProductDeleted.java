package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.services.time.TimeService;

import java.time.Instant;

public class ProductDeleted extends ProductEvent {

    private ProductDeleted(EventId id,
        ProductCode code,
        ProductStatus status,
        String occurredBy,
        Instant occurredOn) {
        super(id, code, status, occurredBy, occurredOn);
    }

    public static ProductDeleted create(Product product, Auth auth) {
        var code = product.getCode();
        var status = product.getStatus();

		var id = EventId.create();
        var occurredBy = auth.who();
        var occurredOn = TimeService.now();

        return new ProductDeleted(id, code, status, occurredBy, occurredOn);
    }
}
