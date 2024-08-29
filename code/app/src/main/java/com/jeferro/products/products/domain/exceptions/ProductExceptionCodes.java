package com.jeferro.products.products.domain.exceptions;

public enum ProductExceptionCodes {
    PRODUCT_NOT_FOUND("E-product-01"),
    PRODUCT_ALREADY_EXISTS("E-product-02");

    public final String value;

    ProductExceptionCodes(String value) {
        this.value = value;
    }
}
