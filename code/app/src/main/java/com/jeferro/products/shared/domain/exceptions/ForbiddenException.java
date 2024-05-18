package com.jeferro.products.shared.domain.exceptions;

import java.util.Set;

import com.jeferro.products.shared.domain.models.auth.Auth;

public class ForbiddenException extends ApplicationException {

    protected ForbiddenException(String message) {
        super(message);
    }

    public static ForbiddenException createOf(Auth auth, Set<String> mandatoryRoles) {
        return new ForbiddenException("Auth " + auth + " has not permission to execute command. Mandatory roles: " + mandatoryRoles);
    }

    public static ForbiddenException createOfNotUserAuth(Auth auth) {
        return new ForbiddenException("Auth isn't a user auth: " + auth);
    }
}
