package com.jeferro.products.shared.infrastructure.integrations.rest.filters.encoders;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

  private static final String BEARER_PREFIX = "Bearer ";

  public String decode(HttpServletRequest request) {
	try {
	  String header = request.getHeader(AUTHORIZATION);

	  if (header == null) {
		return null;
	  }

	  if (!header.startsWith(BEARER_PREFIX)) {
		return null;
	  }

	  String jwtToken = header.substring(BEARER_PREFIX.length());
	  DecodedJWT jwt = JWT.decode(jwtToken);

	  return jwt.getSubject();
	} catch (JWTVerificationException cause) {
	  return null;
	}
  }
}
