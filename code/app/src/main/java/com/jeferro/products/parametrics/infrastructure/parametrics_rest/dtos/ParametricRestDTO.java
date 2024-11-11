package com.jeferro.products.parametrics.infrastructure.parametrics_rest.dtos;

import java.util.List;
import java.util.Map;

public record ParametricRestDTO(
	String id,
	Map<String, String> name,
	List<ParametricValueRestDTO> values
) {

}
