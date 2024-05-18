package com.jeferro.products.shared.application.commands;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import com.jeferro.products.shared.domain.exceptions.ForbiddenException;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.auth.UserAuth;
import com.jeferro.products.shared.domain.models.users.Username;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class Command<R> {

  private final Auth auth;

  public Command(Auth auth) {
	validateAuth(auth);

	this.auth = auth;
  }

  public Auth getAuth() {
	return auth;
  }

  public Username getAuthUsernameOrError() {
	if (auth instanceof UserAuth userAuth) {
	  return userAuth.getUsername();
	}

	throw ForbiddenException.createOfNotUserAuth(auth);
  }

  @Override
  public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object other) {
	if (this == other) {
	  return true;
	}

	if (other == null || getClass() != other.getClass()) {
	  return false;
	}

	return EqualsBuilder.reflectionEquals(
		this,
		other
	);
  }

  @Override
  public String toString() {
	return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
  }

  private static void validateAuth(Auth auth) {
	if (auth == null) {
	  throw ValueValidationException.createOfMessage("Auth is null");
	}
  }
}
