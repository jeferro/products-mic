package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductStatus;
import com.jeferro.shared.domain.events.EventId;

public class ProductDeleted extends ProductEvent {

    private ProductDeleted(EventId id,
        ProductCode code,
        ProductStatus status) {
        super(id, code, status);
    }

    public static ProductDeleted create(Product product) {
        var code = product.getCode();
        var status = product.getStatus();

		var id = EventId.create();

        return new ProductDeleted(id, code, status);
    }
}
