package com.jeferro.products.products.products.domain.models.product_types;

import com.jeferro.shared.ddd.domain.models.aggregates.StringIdentifier;

public class ProductTypeId extends StringIdentifier {

    public ProductTypeId(String value) {
        super(value);
    }

    public static ProductTypeId createOf(String value) {
        return new ProductTypeId(value);
    }
}
