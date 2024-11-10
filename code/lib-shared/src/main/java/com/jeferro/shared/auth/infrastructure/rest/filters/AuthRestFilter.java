package com.jeferro.shared.auth.infrastructure.rest.filters;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import com.jeferro.shared.auth.infrastructure.ContextManager;
import com.jeferro.shared.auth.infrastructure.rest.jwt.HeaderJwtDecoder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class AuthRestFilter extends OncePerRequestFilter {

  private final HeaderJwtDecoder headerJwtDecoder;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	  throws ServletException, IOException {
	authenticateUser(request);

	filterChain.doFilter(request, response);
  }

  private void authenticateUser(HttpServletRequest request) {
	var header = request.getHeader(AUTHORIZATION);

	var auth = headerJwtDecoder.decode(header);

	if (auth == null) {
	  ContextManager.signOut();
	  return;
	}

	ContextManager.signInFromWeb(auth, request);
  }

}
