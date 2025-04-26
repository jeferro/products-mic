package com.jeferro.shared.ddd.domain.models.auth;

import java.util.Set;

public class SystemAuth extends Auth {

    public SystemAuth() {
        super();
    }

    public static SystemAuth create() {
        return new SystemAuth();
    }

    @Override
    public String username() {
        return "system";
    }

    @Override
    public boolean hasRoles(Set<String> mandatoryRoles) {
        return true;
    }
}
