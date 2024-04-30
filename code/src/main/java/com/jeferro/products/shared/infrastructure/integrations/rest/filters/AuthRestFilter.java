package com.jeferro.products.shared.infrastructure.integrations.rest.filters;

import java.io.IOException;

import com.jeferro.products.shared.infrastructure.integrations.rest.filters.encoders.JwtDecoder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
	String username = jwtDecoder.decode(request);

	if (username == null) {
	  SecurityContextHolder.clearContext();

	  filterChain.doFilter(request, response);
	  return;
	}

	Authentication authentication = createAuthenticationToken(request, username);
	SecurityContextHolder.getContext().setAuthentication(authentication);

	filterChain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken createAuthenticationToken(HttpServletRequest request, String username) {
	WebAuthenticationDetailsSource details = new WebAuthenticationDetailsSource();
	details.buildDetails(request);

	UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(
		username,
		null,
		null);
	authentication.setDetails(details);

	return authentication;
  }
}
