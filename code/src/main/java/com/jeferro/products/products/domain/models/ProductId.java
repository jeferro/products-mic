package com.jeferro.products.products.domain.models;

import com.jeferro.products.shared.domain.models.identifiers.SimpleIdentifier;

import java.util.UUID;

public class ProductId extends SimpleIdentifier<String> {

    public ProductId(String value) {
        super(value);
    }

    public static ProductId create() {
        var value = UUID.randomUUID().toString();

        return new ProductId(value);
    }
}
