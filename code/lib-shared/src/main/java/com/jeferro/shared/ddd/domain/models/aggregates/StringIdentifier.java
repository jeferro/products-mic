package com.jeferro.shared.ddd.domain.models.aggregates;

import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public abstract class StringIdentifier extends Identifier {

  private String value;

  public StringIdentifier(String value) {
	setValue(value);
  }

  @Override
  public String toString() {
	return String.valueOf(value);
  }

  private void setValue(String value) {
	ValueValidationUtils.isNotBlank(value, "value", this);
	this.value = value;
  }
}
