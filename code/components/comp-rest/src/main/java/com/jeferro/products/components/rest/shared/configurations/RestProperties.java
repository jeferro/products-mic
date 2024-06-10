package com.jeferro.products.components.rest.shared.configurations;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("components.rest.security")
public record RestProperties(
    String issuer,
    Duration duration,
    String password
) {
    public long durationAsMillis() {
        return duration.toMillis();
    }

    public boolean hasDuration() {
        return duration != null;
    }
}
