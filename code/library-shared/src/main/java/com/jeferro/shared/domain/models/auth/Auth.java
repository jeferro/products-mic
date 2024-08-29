package com.jeferro.shared.domain.models.auth;

import com.jeferro.shared.domain.exceptions.ForbiddenException;
import com.jeferro.shared.domain.models.value_objects.ValueObject;

public abstract class Auth extends ValueObject {

    public abstract String who();

    public Username getUsernameOrError() {
        if (this instanceof UserAuth userAuth) {
            return userAuth.getUsername();
        }

        throw ForbiddenException.createOfNotUserAuth(this);
    }

    @Override
    public String toString() {
        return who();
    }
}
