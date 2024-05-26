package com.jeferro.products.users.domain.models;

import java.util.Set;

import com.jeferro.products.shared.application.Roles;
import com.jeferro.products.shared.domain.models.users.UsernameMother;

public class UserMother {

  public static User user() {
	var username = UsernameMother.user();
	var roles = Set.of(Roles.USER);

	return new User(username, "encoded-password", roles);
  }

  public static User admin() {
	var username = UsernameMother.admin();
	var roles = Set.of(Roles.USER, Roles.ADMIN);

	return new User(username, "encoded-password", roles);
  }
}