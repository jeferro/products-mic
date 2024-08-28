package com.jeferro.shared.infrastructure.adapters.security.services;

import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.models.auth.SystemAuth;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class SecurityManager {

  public void signInFromWeb(Auth auth, HttpServletRequest request) {
	UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(
		auth,
		null,
		null);

	WebAuthenticationDetailsSource details = new WebAuthenticationDetailsSource();
	details.buildDetails(request);

	authentication.setDetails(details);

	SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  public void signInSystem() {
	var auth = SystemAuth.create();

	UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(
		auth,
		null,
		null);

	SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  public void signOut() {
	SecurityContextHolder.clearContext();
  }

  public Auth getAuth() {
	var authentication = SecurityContextHolder.getContext().getAuthentication();

	return authentication != null
		? (Auth) authentication.getPrincipal()
		: null;
  }
}
