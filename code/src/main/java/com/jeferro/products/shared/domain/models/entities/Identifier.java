package com.jeferro.products.shared.domain.models.entities;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class Identifier<T> {

	private final T value;

	public Identifier(T value) {
		validateValue(value);

		this.value = value;
	}

	public T getValue() {
		return value;
	}

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        return EqualsBuilder.reflectionEquals(
                this,
                other
        );
    }

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	private static <T> void validateValue(T value) {
		if (value instanceof String && ((String) value).isBlank()) {
			throw ValueValidationException.ofMessage("Value is blank");
		}
	}
}
