package com.jeferro.shared.ddd.domain.exceptions;

public abstract class ConflictException extends ApplicationException {

    protected ConflictException(String code, String title, String message) {
        super(code, title, message);
    }
}
