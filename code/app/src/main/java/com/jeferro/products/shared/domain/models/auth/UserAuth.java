package com.jeferro.products.shared.domain.models.auth;

import com.jeferro.products.shared.domain.models.users.Username;

import java.util.Set;

public class UserAuth extends Auth {

    private final Username username;

    private final Set<String> roles;

    protected UserAuth(Username username, Set<String> roles) {
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

    public boolean hasAllPermissions(Set<String> mandatoryRoles) {
        return mandatoryRoles.isEmpty()
                || roles.containsAll(mandatoryRoles);
    }

    public boolean belongsToUser(Username username) {
        return this.username.equals(username);
    }
}
