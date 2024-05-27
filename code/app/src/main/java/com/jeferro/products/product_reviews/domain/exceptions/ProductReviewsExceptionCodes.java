package com.jeferro.products.product_reviews.domain.exceptions;

public enum ProductReviewsExceptionCodes {
    REVIEW_NOT_FOUND("E-02-01"),
    REVIEW_ALREADY_EXISTS("E-02-02"),
    FORBIDDEN_REVIEW_OPERATION("E-02-03");

    public final String value;

    ProductReviewsExceptionCodes(String value) {
        this.value = value;
    }
}