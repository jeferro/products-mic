package com.jeferro.products.components.rest.shared.securtiy.configurations;

import com.jeferro.products.components.rest.shared.RestProfile;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import java.time.Duration;

@Profile(RestProfile.NAME)
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
