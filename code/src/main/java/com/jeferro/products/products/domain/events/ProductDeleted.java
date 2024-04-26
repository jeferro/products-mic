package com.jeferro.products.products.domain.events;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;

import java.time.Instant;

public class ProductDeleted extends ProductEvent {

    private ProductDeleted(ProductId productId, String occurredBy, Instant occurredOn) {
        super(productId, occurredBy, occurredOn);
    }

    public static ProductDeleted create(Product product) {
        var productId = product.getId();
        var occurredBy = product.getUpdatedBy();
        var occurredOn = product.getUpdatedAt();

        return new ProductDeleted(productId, occurredBy, occurredOn);
    }
}
