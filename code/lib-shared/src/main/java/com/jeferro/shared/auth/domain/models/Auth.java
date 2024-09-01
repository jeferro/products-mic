package com.jeferro.shared.auth.domain.models;

import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;

public abstract class Auth extends ValueObject {

    public abstract String who();

    @Override
    public String toString() {
        return who();
    }
}
