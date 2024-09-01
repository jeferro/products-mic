package com.jeferro.shared.application;

import java.util.Locale;

import com.jeferro.shared.domain.exceptions.ForbiddenException;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.models.auth.UserAuth;
import com.jeferro.shared.domain.models.auth.Username;

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
