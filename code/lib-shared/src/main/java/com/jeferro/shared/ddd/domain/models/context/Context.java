package com.jeferro.shared.ddd.domain.models.context;

import java.util.Locale;

import com.jeferro.shared.ddd.domain.exceptions.auth.ForbiddenException;
import com.jeferro.shared.ddd.domain.models.auth.Auth;
import com.jeferro.shared.ddd.domain.models.auth.UserAuth;
import com.jeferro.shared.ddd.domain.models.auth.Username;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class Context {

  private Auth auth;

  private Locale locale;

  public Context(Auth auth,
	  Locale locale) {
	setAuth(auth);
	setLocale(locale);
  }

  public Username getUsernameOrError() {
	if (auth instanceof UserAuth userAuth) {
	  return userAuth.getUsername();
	}

	throw ForbiddenException.createOfNotUserAuth(auth);
  }

  private void setAuth(Auth auth) {
	ValueValidationUtils.isNotNull(auth, "auth", this);
	this.auth = auth;
  }

  private void setLocale(Locale locale) {
	ValueValidationUtils.isNotNull(locale, "locale", this);
	this.locale = locale;
  }
}
