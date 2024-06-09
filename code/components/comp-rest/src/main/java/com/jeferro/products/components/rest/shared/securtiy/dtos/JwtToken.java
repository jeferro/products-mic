package com.jeferro.products.components.rest.shared.securtiy.dtos;

import java.util.Set;

public record JwtToken(
	String username,
	Set<String> roles
) {

}
