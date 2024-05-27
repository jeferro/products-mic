package com.jeferro.products.products.domain.exceptions;

public enum ProductExceptionCodes {
    PRODUCT_NOT_FOUND("E-01-01");

    public final String value;

    ProductExceptionCodes(String value) {
        this.value = value;
    }
}
