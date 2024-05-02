package com.jeferro.products.components.rest.shared.securtiy.configurations;

import com.jeferro.products.components.rest.shared.RestProfile;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import java.time.Duration;

@Profile(RestProfile.NAME)
@ConfigurationProperties("components.rest.security")
public class RestSecurityProperties {

    private final String issuer;

    private final Duration duration;

    private final String password;

    public RestSecurityProperties(String issuer, Duration duration, String password) {
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
