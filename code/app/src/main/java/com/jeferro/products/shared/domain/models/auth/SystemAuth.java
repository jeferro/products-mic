package com.jeferro.products.shared.domain.models.auth;

import com.jeferro.products.shared.domain.models.users.Username;

import java.util.Set;

public class SystemAuth extends Auth {

    private final String name;

    protected SystemAuth(String name) {
        super();
        this.name = name;
    }

    public static SystemAuth create(String name) {
        return new SystemAuth(name);
    }

    @Override
    public String who() {
        return name;
    }

    @Override
    public boolean hasPermissions(Set<String> mandatoryRoles) {
        return true;
    }

    @Override
    public boolean belongsToUser(Username username) {
        return false;
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Override
    public boolean isSystem() {
        return true;
    }
}
