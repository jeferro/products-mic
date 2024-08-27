package com.jeferro.shared.infrastructure.adapters.rest.services.jwt;

import java.util.Set;

public record JwtToken(
	String username,
	Set<String> roles
) {

}
