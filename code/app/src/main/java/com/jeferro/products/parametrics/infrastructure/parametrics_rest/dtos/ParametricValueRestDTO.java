package com.jeferro.products.parametrics.infrastructure.parametrics_rest.dtos;

import java.util.List;
import java.util.Map;

public record ParametricValueRestDTO(
	String id,
	Map<String, String> name,
	String value,
	List<ParametricValueRestDTO> values
) {

}
