package com.jeferro.products.products.products.domain.exceptions;

public enum ProductExceptionCodes {
    PRODUCT_NOT_FOUND("E-product-01"),
    PRODUCT_TYPE_NOT_FOUND("E-product-02"),
    PRODUCT_ALREADY_EXISTS("E-product-03");

    public final String value;

    ProductExceptionCodes(String value) {
        this.value = value;
    }
}
