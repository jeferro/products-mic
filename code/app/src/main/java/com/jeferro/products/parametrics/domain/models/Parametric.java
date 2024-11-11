package com.jeferro.products.parametrics.domain.models;

import com.jeferro.products.parametrics.domain.exceptions.ParametricValueNotFoundException;
import com.jeferro.products.parametrics.domain.models.values.ParametricValueId;
import com.jeferro.products.parametrics.domain.models.values.ParametricValues;
import com.jeferro.shared.ddd.domain.models.projection.Projection;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class Parametric extends Projection<ParametricId> {

  private LocalizedField name;

  private ParametricValues values;

  public Parametric(
	  ParametricId id,
	  LocalizedField name,
	  ParametricValues values) {
	super(id);

	setName(name);
	setValues(values);
  }

  public static Parametric createOf(ParametricId id, LocalizedField name, ParametricValues values) {
	return new Parametric(id, name, values);
  }

  public boolean validate(ParametricValueId parametricValueId) {
	if (notContainsParametricValue(parametricValueId)) {
	  throw ParametricValueNotFoundException.createOf(this, parametricValueId);
	}

	return true;
  }

  private boolean notContainsParametricValue(ParametricValueId parametricValueId) {
	return values.stream()
		.noneMatch(parametricValue -> parametricValue.hasSameId(parametricValueId));
  }

  public void setName(LocalizedField name) {
	ValueValidationUtils.isNotNull(name, "name", this);
	this.name = name;
  }

  private void setValues(ParametricValues values) {
	ValueValidationUtils.isNotEmpty(values, "values", this);
	this.values = values;
  }
}
