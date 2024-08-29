package com.jeferro.products.products.products.domain.models;

import com.jeferro.products.products.products.domain.models.ProductCode;

public class ProductCodeMother {

    public static ProductCode appleCode() {
        return new ProductCode("apple");
    }

    public static ProductCode pearCode() {
        return new ProductCode("pear");
    }

}