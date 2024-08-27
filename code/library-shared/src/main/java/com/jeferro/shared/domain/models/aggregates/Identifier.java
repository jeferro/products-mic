package com.jeferro.shared.domain.models.aggregates;

import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class Identifier<T> {

  protected static final String SEPARATOR = "::";

  private T value;

  public Identifier(T value) {
	setValue(value);
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

	if (other == null || getClass() != other.getClass()) {
	  return false;
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

  private void setValue(T value) {
	if (value instanceof String && ((String) value).isBlank()) {
	  throw ValueValidationException.createOfMessage("Value is blank");
	}

	this.value = value;
  }
}
