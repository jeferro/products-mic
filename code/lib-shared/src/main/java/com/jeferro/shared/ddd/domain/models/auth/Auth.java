package com.jeferro.shared.ddd.domain.models.auth;

import java.util.Set;

import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;

public abstract class Auth extends ValueObject {

    public abstract String who();

    public abstract boolean hasRoles(Set<String> mandatoryRoles);

    @Override
    public String toString() {
        return who();
    }
}
