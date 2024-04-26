package com.jeferro.products.products.domain.events;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;

import java.time.Instant;

public class ProductUpdated extends ProductEvent {

    private ProductUpdated(ProductId productId, String occurredBy, Instant occurredOn) {
        super(productId, occurredBy, occurredOn);
    }

    public static ProductUpdated create(Product product) {
        var productId = product.getId();
        var occurredBy = product.getUpdatedBy();
        var occurredOn = product.getUpdatedAt();

        return new ProductUpdated(productId, occurredBy, occurredOn);
    }
}
