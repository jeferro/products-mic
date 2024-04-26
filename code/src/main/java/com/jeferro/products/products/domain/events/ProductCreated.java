package com.jeferro.products.products.domain.events;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;

import java.time.Instant;

public class ProductCreated extends ProductEvent {

    private ProductCreated(ProductId productId, String occurredBy, Instant occurredOn) {
        super(productId, occurredBy, occurredOn);
    }

    public static ProductCreated create(Product product) {
        var productId = product.getId();
        var occurredBy = product.getCreatedBy();
        var occurredAt = product.getCreatedAt();

        return new ProductCreated(productId, occurredBy, occurredAt);
    }
}
