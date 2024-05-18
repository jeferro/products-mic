package com.jeferro.products.shared.domain.models.auth;

import com.jeferro.products.shared.domain.models.value_objects.ValueObject;

public abstract class Auth extends ValueObject {

    public abstract String who();

    @Override
    public String toString() {
        return who();
    }
}
