package com.jeferro.shared.ddd.application;

import java.util.Locale;

import com.jeferro.shared.ddd.domain.exceptions.ForbiddenException;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.auth.domain.models.Auth;
import com.jeferro.shared.auth.domain.models.UserAuth;
import com.jeferro.shared.auth.domain.models.Username;

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
	if(auth == null){
	  throw ValueValidationException.createOfMessage("Auth is null");
	}

	this.auth = auth;
  }

  public Locale getLocale() {
	return locale;
  }

  private void setLocale(Locale locale) {
	if(locale == null){
	  throw ValueValidationException.createOfMessage("Locale is null");
	}

	this.locale = locale;
  }
}
