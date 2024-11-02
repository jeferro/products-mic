package com.jeferro.shared.auth.infrastructure.rest.filters;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import com.jeferro.shared.auth.infrastructure.rest.jwt.JwtDecoder;
import com.jeferro.shared.auth.infrastructure.ContextManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthRestFilter extends OncePerRequestFilter {

  private final JwtDecoder jwtDecoder;

  public AuthRestFilter(JwtDecoder jwtDecoder) {
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
	  ContextManager.signOut();
	  return;
	}

	ContextManager.signInFromWeb(auth, request);
  }

}
