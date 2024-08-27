package com.jeferro.shared.domain.exceptions;

import com.jeferro.shared.domain.models.auth.Auth;

import java.util.Set;

public class ForbiddenException extends ApplicationException {

    protected ForbiddenException(String message) {
        super(SharedExceptionCodes.FORBIDDEN.value, "Forbidden", message);
    }

    public static ForbiddenException createOf(Auth auth, Set<String> mandatoryRoles) {
        return new ForbiddenException("Auth " + auth + " has not permission to execute command. Mandatory roles: " + mandatoryRoles);
    }

    public static ForbiddenException createOfNotUserAuth(Auth auth) {
        return new ForbiddenException("Auth isn't a user auth: " + auth);
    }
}
