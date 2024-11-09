package com.jeferro.products.parametrics.infrastructure.rest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import com.jeferro.products.parametrics.domain.models.Parametric;
import com.jeferro.products.parametrics.domain.models.ParametricId;
import com.jeferro.products.parametrics.domain.models.ParametricValue;
import com.jeferro.products.parametrics.domain.models.ParametricValueId;
import com.jeferro.products.parametrics.domain.services.ParametricFinder;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import org.springframework.stereotype.Component;

@Component
public class ParametricsInMemoryFinder implements ParametricFinder {

  private final Set<Parametric> parametrics = Set.of(
	  mockProductType()
  );

  @Override
  public Optional<Parametric> findById(ParametricId parametricId) {
	return parametrics.stream()
		.filter(parametric -> parametric.hasSameId(parametricId))
		.findFirst();
  }

  private Parametric mockProductType() {
	var values = new ArrayList<ParametricValue>();

	var fruitValueId = ParametricValueId.createOf("fruit");
	var fruitValue = ParametricValue.createSimple(
		fruitValueId,
		LocalizedField.createOfUS("Fruit"));
	values.add(fruitValue);

	var vegetablesValueId = ParametricValueId.createOf("vegetables");
	var vegetablesValue = ParametricValue.createSimple(
		vegetablesValueId,
		LocalizedField.createOfUS("Vegetables"));
	values.add(vegetablesValue);

	var productTypeId = ParametricId.createOf("product-types");
	return Parametric.createOf(
		productTypeId,
		LocalizedField.createOfUS("Product Types"),
		values);
  }
}
