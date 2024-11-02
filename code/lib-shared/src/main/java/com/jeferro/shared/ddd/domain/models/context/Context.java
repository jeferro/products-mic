package com.jeferro.shared.ddd.domain.models.context;

import java.util.Locale;

import com.jeferro.shared.ddd.domain.exceptions.auth.ForbiddenException;
import com.jeferro.shared.ddd.domain.models.auth.Auth;
import com.jeferro.shared.ddd.domain.models.auth.UserAuth;
import com.jeferro.shared.ddd.domain.models.auth.Username;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;

public class Context {

  private Auth auth;

  private Locale locale;

  public Context(Auth auth,
	  Locale locale) {
	setAuth(auth);
	setLocale(locale);
  }

  public Auth getAuth() {
	return auth;
  }

  public Username getUsernameOrError() {
	if (auth instanceof UserAuth userAuth) {
	  return userAuth.getUsername();
	}

	throw ForbiddenException.createOfNotUserAuth(auth);
  }

  private void setAuth(Auth auth) {
	ValueValidationUtils.isNotNull(auth, "Auth");
	this.auth = auth;
  }

  public Locale getLocale() {
	return locale;
  }

  private void setLocale(Locale locale) {
	ValueValidationUtils.isNotNull(locale, "Locale");
	this.locale = locale;
  }
}
