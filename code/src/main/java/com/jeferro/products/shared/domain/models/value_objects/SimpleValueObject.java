package com.jeferro.products.shared.domain.models.value_objects;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;

import java.io.Serializable;

public class SimpleValueObject<T extends Serializable> extends ValueObject {

    private final T value;

    public SimpleValueObject(T value) {
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
