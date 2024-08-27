package com.jeferro.shared.domain.models.auth;

import com.jeferro.shared.domain.models.value_objects.ValueObject;

public abstract class Auth extends ValueObject {

    public abstract String who();

    public abstract boolean isUser();

    @Override
    public String toString() {
        return who();
    }
}
