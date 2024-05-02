package com.jeferro.products.shared.domain.exceptions;

public abstract class NotFoundException extends ApplicationException {

    protected NotFoundException(String message) {
        super(message);
    }
}
