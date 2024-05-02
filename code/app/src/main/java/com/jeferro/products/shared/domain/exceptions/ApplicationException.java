package com.jeferro.products.shared.domain.exceptions;

public abstract class ApplicationException extends RuntimeException {

    protected ApplicationException(String message) {
        super(message);
    }

    protected ApplicationException(Exception cause) {
        super(cause);
    }
}
