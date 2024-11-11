package com.jeferro.shared.ddd.domain.exceptions;

public enum SharedExceptionCodes {
    INTERNAL_ERROR("shared-internal-error"),
    VALUE_VALIDATION_ERROR("shared-value-validation"),
    FORBIDDEN("shared-forbidden"),
    UNAUTHORIZED("shared-unauthorized");

    public final String value;

    SharedExceptionCodes(String value) {
        this.value = value;
    }
}
