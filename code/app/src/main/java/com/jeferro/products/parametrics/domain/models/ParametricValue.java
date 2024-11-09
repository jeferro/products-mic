package com.jeferro.products.parametrics.domain.models;

import java.util.List;

import com.jeferro.shared.ddd.domain.models.projection.Projection;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class ParametricValue extends Projection<ParametricValueId> {

  private LocalizedField name;

  private String value;

  private List<ParametricValue> values;

  public ParametricValue(
	  ParametricValueId id,
	  LocalizedField name,
	  String value,
	  List<ParametricValue> values) {
	super(id);

	setName(name);
	setValue(value);
	setValues(values);
  }

  public static ParametricValue createSimple(ParametricValueId id, LocalizedField name) {
	return new ParametricValue(id, name, null, null);
  }

  private void setName(LocalizedField name) {
	ValueValidationUtils.isNotNull(name, "name", this);
	this.name = name;
  }

  private void setValue(String value) {
	this.value = value;
  }

  private void setValues(List<ParametricValue> values) {
	this.values = values;
  }
}
