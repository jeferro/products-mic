package com.jeferro.shared.ddd.domain.models.auth;

import java.util.Set;

public class AnonymousAuth extends Auth {

    public AnonymousAuth() {
        super();
    }

    public static AnonymousAuth create() {
        return new AnonymousAuth();
    }

    @Override
    public String username() {
        return "anonymous";
    }

    @Override
    public boolean hasRoles(Set<String> mandatoryRoles) {
        return mandatoryRoles.isEmpty();
    }
}
