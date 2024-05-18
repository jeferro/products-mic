package com.jeferro.products.components.rest.shared.securtiy.configurations;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("components.rest.security")
public record RestSecurityProperties(
    String issuer,
    Duration duration,
    String password
) {
    public long durationAsMillis() {
        return duration.toMillis();
    }
}
