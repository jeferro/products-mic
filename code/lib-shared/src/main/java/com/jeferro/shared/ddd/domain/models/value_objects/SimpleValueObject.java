package com.jeferro.shared.ddd.domain.models.value_objects;

import java.io.Serializable;

import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class SimpleValueObject<T extends Serializable> extends ValueObject {

  private T value;

  public SimpleValueObject(T value) {
	setValue(value);
  }

  @Override
  public String toString() {
	return String.valueOf(value);
  }

  private void setValue(T value) {
	ValueValidationUtils.isNotNull(value, "Value");
	this.value = value;
  }
}
