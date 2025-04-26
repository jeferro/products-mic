package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.events.EventId;

public class ProductPublished extends ProductEvent {

    private ProductPublished(EventId id,
                             ProductCode code) {
        super(id, code);
    }

    public static ProductPublished create(Product product) {
        var id = EventId.create();

        var code = product.getCode();

        return new ProductPublished(id, code);
    }
}
