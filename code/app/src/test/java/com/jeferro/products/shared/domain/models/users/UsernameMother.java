package com.jeferro.products.shared.domain.models.users;

public abstract class UsernameMother {

  public static Username user() {
	return new Username("user");
  }

  public static Username admin() {
	return new Username("admin");
  }
}