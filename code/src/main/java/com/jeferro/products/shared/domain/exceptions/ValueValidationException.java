package com.jeferro.products.shared.domain.exceptions;

public class ValueValidationException extends ApplicationException {

    private ValueValidationException(String message){
        super(message);
    }

    public static ValueValidationException ofMessage(String message) {
        return new ValueValidationException(message);
    }
}
