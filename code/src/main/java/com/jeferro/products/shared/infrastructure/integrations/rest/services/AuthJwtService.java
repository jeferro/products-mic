package com.jeferro.products.shared.infrastructure.integrations.rest.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jeferro.products.shared.domain.models.auth.AnonymousAuth;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.auth.UserAuth;
import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.infrastructure.integrations.rest.configurations.JwtProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Service
public class AuthJwtService {

    private static final Logger logger = LoggerFactory.getLogger(AuthJwtService.class);

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String ROLES_CLAIM = "roles";

    private final JwtProperties jwtProperties;

    private final Algorithm hmac512;

    private final JWTVerifier jwtVerifier;

    public AuthJwtService(JwtProperties jwtProperties) {
        hmac512 = Algorithm.HMAC512(jwtProperties.getIssuer());
        jwtVerifier = JWT.require(hmac512).build();

        this.jwtProperties = jwtProperties;
    }

    public String calculateJwtHeader(String username, Set<String> roles) {
        var issuedAt = Instant.now();
        var expiresAt = issuedAt.plusMillis(jwtProperties.getDurationAsMillis());

        var token = JWT.create()
                .withIssuer(jwtProperties.getIssuer())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(username)
                .withArrayClaim(ROLES_CLAIM, roles.toArray(String[]::new))
                .sign(hmac512);

        return BEARER_PREFIX + token;
    }

    public Auth getUserAuth(String authorization) {
        try {
            if (authorization == null) {
                return AnonymousAuth.create();
            }

            if (!authorization.startsWith(BEARER_PREFIX)) {
                return AnonymousAuth.create();
            }

            var token = authorization.substring(BEARER_PREFIX.length());

            var jwt = jwtVerifier.verify(token);
            var userId = new Username(jwt.getSubject());

            var roles = jwt.getClaim(ROLES_CLAIM)
                    .asArray(String.class);

            return UserAuth.create(userId, Set.of(roles));
        } catch (JWTVerificationException cause) {
            logger.error(cause.getMessage(), cause);

            return AnonymousAuth.create();
        }
    }
}
