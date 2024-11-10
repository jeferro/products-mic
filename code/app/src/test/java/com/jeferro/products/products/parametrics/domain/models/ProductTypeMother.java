package com.jeferro.products.products.parametrics.domain.models;

import com.jeferro.products.parametrics.domain.models.values.ParametricValue;
import com.jeferro.products.parametrics.domain.models.values.ParametricValueId;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import org.jetbrains.annotations.NotNull;

public class ProductTypeMother {

  public static ParametricValue fruit() {
	var fruitValueId = fruitId();
	return ParametricValue.createSimple(
		fruitValueId,
		LocalizedField.createOfUS("Fruit"));
  }

  @NotNull
  public static ParametricValueId fruitId() {
	return ParametricValueId.createOf("fruit");
  }
}
