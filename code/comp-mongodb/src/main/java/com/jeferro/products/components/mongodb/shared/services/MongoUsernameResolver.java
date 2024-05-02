package com.jeferro.products.components.mongodb.shared.services;

import com.jeferro.products.components.mongodb.MongoProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Profile(MongoProfile.NAME)
public class MongoUsernameResolver {

    private static final String UNKNOWN = "unknown";

    public String resolve() {
        final Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication == null) {
            return UNKNOWN;
        }

        final Object principal = authentication.getPrincipal();

        if (principal == null) {
            return UNKNOWN;
        }

        return (String) principal;
    }
}
