package com.jeferro.products.shared.domain.exceptions;

import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.users.Username;

public class ForbiddenException extends ApplicationException {

    protected ForbiddenException(String message) {
        super(message);
    }

    public static ForbiddenException createOfRoles(Auth auth) {
        return new ForbiddenException("Auth " + auth + " has not mandatory roles");
    }

    public static ForbiddenException createOfUser(Auth auth, Username username) {
        return new ForbiddenException("Auth " + auth + " don' belong to user " + username);
    }
}
