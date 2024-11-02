package com.jeferro.shared.ddd.domain.models.auth;

import java.util.Set;

public class UserAuth extends Auth {

    private final Username username;

    private final Set<String> roles;

    public UserAuth(Username username, Set<String> roles) {
        super();

        this.username = username;
        this.roles = roles;
    }

    public static UserAuth create(Username username, Set<String> roles) {
        return new UserAuth(username, roles);
    }

    public Username getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public String who() {
        return username.toString();
    }

    @Override
    public boolean hasRoles(Set<String> mandatoryRoles) {
        return mandatoryRoles.isEmpty()
            || roles.containsAll(mandatoryRoles);
    }
}