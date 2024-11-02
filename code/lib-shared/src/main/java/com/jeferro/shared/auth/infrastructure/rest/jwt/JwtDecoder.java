package com.jeferro.shared.auth.infrastructure.rest.jwt;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jeferro.shared.ddd.domain.models.auth.Auth;
import com.jeferro.shared.ddd.domain.models.auth.UserAuth;
import com.jeferro.shared.ddd.domain.models.auth.Username;
import com.jeferro.shared.auth.infrastructure.rest.configurations.RestSecurityProperties;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

  private static final String BEARER_PREFIX = "Bearer ";

  public static final String ROLES_CLAIM = "roles";

  private final RestSecurityProperties jwtProperties;

  private final Algorithm hmac512;

  private final JWTVerifier jwtVerifier;

  public JwtDecoder(RestSecurityProperties restSecurityProperties) {
	hmac512 = Algorithm.HMAC512(restSecurityProperties.issuer());
	jwtVerifier = JWT.require(hmac512).build();

	this.jwtProperties = restSecurityProperties;
  }

  public boolean belongsToJwt(String header) {
	return header.startsWith(BEARER_PREFIX);
  }

  public Auth decode(String header) {
	try {
	  if (header == null
		  || !belongsToJwt(header)) {
		return null;
	  }

	  var jwtToken = header.substring(BEARER_PREFIX.length());
	  var jwt = jwtVerifier.verify(jwtToken);

	  var username = new Username(jwt.getSubject());
	  var roles = new HashSet<>(jwt.getClaim(ROLES_CLAIM)
		  .asList(String.class));

	  return UserAuth.create(username, roles);
	} catch (JWTVerificationException cause) {
	  return null;
	}
  }

  public String encode(Username username, Set<String> roles) {
	var issuedAt = Instant.now();

	var jwtBuilder = JWT.create()
		.withIssuer(jwtProperties.issuer())
		.withIssuedAt(issuedAt)
		.withSubject(username.getValue())
		.withArrayClaim(ROLES_CLAIM, roles.toArray(String[]::new));

	if (jwtProperties.hasDuration()) {
	  var expiresAt = issuedAt.plusMillis(jwtProperties.durationAsMillis());

	  jwtBuilder.withExpiresAt(expiresAt);
	}

	return jwtBuilder.sign(hmac512);
  }
}
