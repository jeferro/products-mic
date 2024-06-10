package com.jeferro.products.components.rest.shared.dtos;

import java.util.Set;

public record JwtToken(
	String username,
	Set<String> roles
) {

}
