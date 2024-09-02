package com.jeferro.shared.auth.domain.models;

import java.util.Set;

public class SystemAuth extends Auth {

    public SystemAuth() {
        super();
    }

    public static SystemAuth create() {
        return new SystemAuth();
    }

    @Override
    public String who() {
        return "system";
    }

    @Override
    public boolean hasRoles(Set<String> mandatoryRoles) {
        return true;
    }
}
