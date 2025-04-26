package com.jeferro.shared.ddd.domain.models.auth;

import lombok.Getter;

import java.util.Set;

@Getter
public class UserAuth extends Auth {

    private final String username;

    private final Set<String> roles;

    public UserAuth(String username, Set<String> roles) {
        super();

        this.username = username;
        this.roles = roles;
    }

    public static UserAuth create(String username, Set<String> roles) {
        return new UserAuth(username, roles);
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public boolean hasRoles(Set<String> mandatoryRoles) {
        return mandatoryRoles.isEmpty()
                || roles.containsAll(mandatoryRoles);
    }
}
