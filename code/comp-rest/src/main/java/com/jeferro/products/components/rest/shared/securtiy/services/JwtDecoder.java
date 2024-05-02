package com.jeferro.products.components.rest.shared.securtiy.services;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.time.Instant;
import java.util.HashSet;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.components.rest.shared.securtiy.configurations.RestSecurityProperties;
import com.jeferro.products.components.rest.shared.securtiy.dtos.JwtToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(RestProfile.NAME)
public class JwtDecoder {

	private static final String BEARER_PREFIX = "Bearer ";

	public static final String ROLES_CLAIM = "roles";

	private final RestSecurityProperties jwtProperties;

	private final Algorithm hmac512;

	private final JWTVerifier jwtVerifier;

	public JwtDecoder(RestSecurityProperties restSecurityProperties) {
		hmac512 = Algorithm.HMAC512(restSecurityProperties.getIssuer());
		jwtVerifier = JWT.require(hmac512).build();

		this.jwtProperties = restSecurityProperties;
	}

	public JwtToken decode(HttpServletRequest request) {
		try {
			var header = request.getHeader(AUTHORIZATION);

			if (header == null) {
				return null;
			}

			if (!header.startsWith(BEARER_PREFIX)) {
				return null;
			}

			var jwtToken = header.substring(BEARER_PREFIX.length());
			var jwt = jwtVerifier.verify(jwtToken);

			var username = jwt.getSubject();
			var roles = new HashSet<>(jwt.getClaim(ROLES_CLAIM)
				.asList(String.class));

			return new JwtToken(username, roles);
		} catch (JWTVerificationException cause) {
			return null;
		}
	}

	public String encode(JwtToken jwtToken) {
		var issuedAt = Instant.now();
		var expiresAt = issuedAt.plusMillis(jwtProperties.getDurationAsMillis());

		var token = JWT.create()
			.withIssuer(jwtProperties.getIssuer())
			.withIssuedAt(issuedAt)
			.withExpiresAt(expiresAt)
			.withSubject(jwtToken.username())
			.withArrayClaim(ROLES_CLAIM, jwtToken.roles().toArray(String[]::new))
			.sign(hmac512);

		return BEARER_PREFIX + token;
	}
}
