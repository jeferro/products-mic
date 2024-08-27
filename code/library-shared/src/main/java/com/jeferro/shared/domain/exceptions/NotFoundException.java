package com.jeferro.shared.domain.exceptions;

public abstract class NotFoundException extends ApplicationException {

    protected NotFoundException(String code, String title, String message) {
        super(code, title, message);
    }
}
