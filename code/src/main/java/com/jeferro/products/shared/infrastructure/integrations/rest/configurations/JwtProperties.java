package com.jeferro.products.shared.infrastructure.integrations.rest.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties("infrastructure.jwt")
public class JwtProperties {

    private final String issuer;

    private final Duration duration;

    private final String password;

    public JwtProperties(String issuer, Duration duration, String password) {
        this.issuer = issuer;
        this.duration = duration;
        this.password = password;
    }

    public String getIssuer() {
        return issuer;
    }

    public long getDurationAsMillis() {
        return duration.toMillis();
    }

    public String getPassword() {
        return password;
    }
}
