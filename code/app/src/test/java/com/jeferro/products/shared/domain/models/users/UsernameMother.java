package com.jeferro.products.shared.domain.models.users;

import com.jeferro.shared.domain.models.auth.Username;

public abstract class UsernameMother {

  public static Username user() {
	return new Username("user");
  }

  public static Username admin() {
	return new Username("admin");
  }
}