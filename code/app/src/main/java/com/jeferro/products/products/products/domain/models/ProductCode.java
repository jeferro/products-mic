package com.jeferro.products.products.products.domain.models;

import com.jeferro.shared.ddd.domain.models.aggregates.Identifier;

public class ProductCode extends Identifier<String> {

    public ProductCode(String value) {
        super(value);
    }
}
