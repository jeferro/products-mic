package com.jeferro.products.shared.domain.exceptions.internals;

public class ValueValidationException extends InternalErrorException {

    private ValueValidationException(String message){
        super(message);
    }

    public static ValueValidationException createOfMessage(String message) {
        return new ValueValidationException(message);
    }
}
