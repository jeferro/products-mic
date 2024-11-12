package com.jeferro.products.products.parametrics.domain.models;

import com.jeferro.products.parametrics.domain.models.values.ParametricValue;
import com.jeferro.products.parametrics.domain.models.values.ParametricValueId;
import com.jeferro.shared.locale.domain.models.LocalizedField;

public class ProductTypeMother {

  public static ParametricValue fruit() {
	var fruitValueId = fruitId();
	return ParametricValue.createSimple(
		fruitValueId,
		LocalizedField.createOfUS("Fruit"));
  }

  public static ParametricValueId fruitId() {
	return ParametricValueId.createOf("fruit");
  }
}
