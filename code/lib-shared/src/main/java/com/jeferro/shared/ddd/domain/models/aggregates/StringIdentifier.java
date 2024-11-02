package com.jeferro.shared.ddd.domain.models.aggregates;

import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;

public abstract class StringIdentifier extends Identifier {

  private String value;

  public StringIdentifier(String value) {
	setValue(value);
  }

  public String getValue() {
	return value;
  }

  @Override
  public String toString() {
	return String.valueOf(value);
  }

  private void setValue(String value) {
	ValueValidationUtils.isNotBlank(value, "Value");
	this.value = value;
  }
}
