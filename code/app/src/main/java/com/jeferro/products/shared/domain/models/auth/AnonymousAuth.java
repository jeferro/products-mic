package com.jeferro.products.shared.domain.models.auth;

import com.jeferro.products.shared.domain.models.users.Username;

import java.util.Set;

public class AnonymousAuth extends Auth {

    protected AnonymousAuth() {
        super();
    }

    public static AnonymousAuth create() {
        return new AnonymousAuth();
    }

    @Override
    public String who() {
        return "anonymous";
    }

    @Override
    public boolean hasPermissions(Set<String> mandatoryRoles) {
        return mandatoryRoles.isEmpty();
    }

    @Override
    public boolean belongsToUser(Username username) {
        return false;
    }

    @Override
    public boolean isAnonymous() {
        return true;
    }

    @Override
    public boolean isSystem() {
        return false;
    }
}
