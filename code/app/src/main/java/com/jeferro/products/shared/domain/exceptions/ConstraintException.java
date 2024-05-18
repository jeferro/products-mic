package com.jeferro.products.shared.domain.exceptions;

public abstract class ConstraintException extends ApplicationException {

    protected ConstraintException(String message) {
        super(message);
    }
}
