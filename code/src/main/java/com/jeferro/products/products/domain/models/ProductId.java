package com.jeferro.products.products.domain.models;

import java.util.UUID;

import com.jeferro.products.shared.domain.models.entities.Identifier;

public class ProductId extends Identifier<String> {

    public ProductId(String value) {
        super(value);
    }

    public static ProductId create() {
        var value = UUID.randomUUID().toString();

        return new ProductId(value);
    }
}
