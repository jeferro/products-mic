package com.jeferro.products.users.users.domain.models;

import java.util.Set;

import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.ddd.domain.models.auth.Username;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class User extends AggregateRoot<Username> {

  private String encodedPassword;

  private Set<String> roles;

  public User(Username id, String encodedPassword, Set<String> roles) {
	super(id);

	setEncodedPassword(encodedPassword);
	setRoles(roles);
  }

  public Username getUsername() {
	return id;
  }

  private void setEncodedPassword(String encodedPassword) {
	ValueValidationUtils.isNotBlank(encodedPassword, "encodedPassword", this);
	this.encodedPassword = encodedPassword;
  }

  private void setRoles(Set<String> roles) {
	ValueValidationUtils.isNotEmpty(roles, "roles", this);
	this.roles = roles;
  }
}
