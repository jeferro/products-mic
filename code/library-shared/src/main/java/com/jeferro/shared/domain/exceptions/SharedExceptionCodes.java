package com.jeferro.shared.domain.exceptions;

public enum SharedExceptionCodes {
    INTERNAL_ERROR("E-00-01"),
    FORBIDDEN("E-00-02"),
    UNAUTHORIZED("E-00-03");

    public final String value;

    SharedExceptionCodes(String value) {
        this.value = value;
    }
}
