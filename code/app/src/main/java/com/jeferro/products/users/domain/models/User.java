package com.jeferro.products.users.domain.models;

import java.util.Set;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.aggregates.AggregateRoot;
import com.jeferro.products.shared.domain.models.users.Username;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class User extends AggregateRoot<Username> {

  private String encodedPassword;

  private Set<String> roles;

  public User(Username username, String encodedPassword, Set<String> roles) {
	super(username);

	setEncodedPassword(encodedPassword);
	setRoles(roles);
  }

  public Username getUsername() {
	return id;
  }

  public String getEncodedPassword() {
	return encodedPassword;
  }

  public Set<String> getRoles() {
	return roles;
  }

  private void setEncodedPassword(String encodedPassword) {
	if (StringUtils.isBlank(encodedPassword)) {
	  throw ValueValidationException.createOfMessage("Encoded password is blank");
	}

	this.encodedPassword = encodedPassword;
  }

  private void setRoles(Set<String> roles) {
	if (CollectionUtils.isEmpty(roles)) {
	  throw ValueValidationException.createOfMessage("Roles is empty");
	}

	this.roles = roles;
  }
}
