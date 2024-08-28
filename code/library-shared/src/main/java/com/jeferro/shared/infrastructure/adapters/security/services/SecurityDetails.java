package com.jeferro.shared.infrastructure.adapters.security.services;

import java.util.Set;

public record SecurityDetails(
	String username,
	Set<String> roles,
	boolean isSystem
) {

  public static SecurityDetails createOfSystem() {
	return new SecurityDetails(null, null, true);
  }

  public static SecurityDetails createOfUser(String username, Set<String> roles) {
	return new SecurityDetails(username, roles, false);
  }

}
