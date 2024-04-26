package com.jeferro.products.products.domain.models;

import com.jeferro.products.shared.domain.models.metadata.Metadata;
import com.jeferro.products.shared.domain.services.time.TimeService;

public class ProductMother {

    public static Product apple() {
        var productId = new ProductId("apple");

        var now = TimeService.now();
        var metadata = new Metadata(now, "user", now, "user");

        return new Product(productId, "Apple", metadata);
    }

    public static Product pear() {
        var productId = new ProductId("pear");

        var now = TimeService.now();
        var metadata = new Metadata(now, "user", now, "user");

        return new Product(productId, "Pear", metadata);
    }

}