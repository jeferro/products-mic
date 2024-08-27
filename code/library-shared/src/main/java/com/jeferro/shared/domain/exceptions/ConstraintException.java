package com.jeferro.shared.domain.exceptions;

public abstract class ConstraintException extends ApplicationException {

    protected ConstraintException(String code, String title, String message) {
        super(code, title, message);
    }
}
