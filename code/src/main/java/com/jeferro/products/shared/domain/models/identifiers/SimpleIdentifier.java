package com.jeferro.products.shared.domain.models.identifiers;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;

import java.io.Serializable;

public class SimpleIdentifier<T extends Serializable> extends Identifier {

    // TODO: Remove class and move code to Identifier
    private final T value;

    public SimpleIdentifier(T value) {
        if (value instanceof String && ((String) value).isBlank()) {
            throw ValueValidationException.ofMessage("Value is blank");
        }

        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
