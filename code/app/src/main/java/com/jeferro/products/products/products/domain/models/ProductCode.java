package com.jeferro.products.products.products.domain.models;

import java.util.UUID;

import com.jeferro.shared.domain.models.aggregates.Identifier;

public class ProductCode extends Identifier<String> {

    public ProductCode(String value) {
        super(value);
    }
}
