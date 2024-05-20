package com.jeferro.products.shared.domain.models.value_objects;

import java.io.Serializable;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;

public class SimpleValueObject<T extends Serializable> extends ValueObject {

  private T value;

  public SimpleValueObject(T value) {
	setValue(value);
  }

  public T getValue() {
	return value;
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
