package com.jeferro.shared.infrastructure.adapters.rest.filters;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import com.jeferro.shared.infrastructure.adapters.rest.services.jwt.JwtDecoder;
import com.jeferro.shared.infrastructure.adapters.security.services.SecurityManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthRestFilter extends OncePerRequestFilter {

  private final SecurityManager securityManager;

  private final JwtDecoder jwtDecoder;

  public AuthRestFilter(SecurityManager securityManager,
	  JwtDecoder jwtDecoder) {
	this.securityManager = securityManager;
	this.jwtDecoder = jwtDecoder;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	  throws ServletException, IOException {
	authenticateUser(request);

	filterChain.doFilter(request, response);
  }

  private void authenticateUser(HttpServletRequest request) {
	var header = request.getHeader(AUTHORIZATION);

	var auth = jwtDecoder.decode(header);

	if (auth == null) {
	  securityManager.signOut();
	  return;
	}

	securityManager.signInFromWeb(auth, request);
  }

}
