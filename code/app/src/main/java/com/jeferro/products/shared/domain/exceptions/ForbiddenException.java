package com.jeferro.products.shared.domain.exceptions;

import com.jeferro.products.shared.domain.models.auth.Auth;

public class ForbiddenException extends ApplicationException {

    protected ForbiddenException(String message) {
        super(message);
    }

    public static ForbiddenException createOfRoles(Auth auth) {
        return new ForbiddenException("Auth " + auth + " has not mandatory roles");
    }

    public static ForbiddenException createOfUser(Auth auth) {
        return new ForbiddenException("Auth " + auth + " is not a user");
    }
}
