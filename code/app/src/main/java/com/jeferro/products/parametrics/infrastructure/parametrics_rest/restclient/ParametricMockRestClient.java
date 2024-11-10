package com.jeferro.products.parametrics.infrastructure.parametrics_rest.restclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jeferro.products.parametrics.infrastructure.parametrics_rest.dtos.ParametricRestDTO;
import com.jeferro.products.parametrics.infrastructure.parametrics_rest.dtos.ParametricValueRestDTO;
import org.springframework.stereotype.Service;

@Service
public class ParametricMockRestClient {

  private final List<ParametricRestDTO> parametrics = List.of(
	  mockProductType()
  );

  public ParametricRestDTO findById(String parametricId) {
	return parametrics.stream()
		.filter(parametric -> parametric.id().equals(parametricId))
		.findFirst()
		.orElse(null);
  }

  public List<ParametricRestDTO> findByDomain(String domain) {
	return parametrics;
  }

  private ParametricRestDTO mockProductType() {
	var values = new ArrayList<ParametricValueRestDTO>();

	var fruitValue = new ParametricValueRestDTO(
		"fruit",
		Map.of("en-US", "Fruit"),
		null,
		null);
	values.add(fruitValue);

	var vegetablesValue = new ParametricValueRestDTO(
		"vegetables",
		Map.of("en-US", "Vegetables"),
		null,
		null);
	values.add(vegetablesValue);

	return new ParametricRestDTO(
		"product-types",
		Map.of("en-US", "Product Types"),
		values);
  }
}
