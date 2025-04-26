package com.jeferro.shared.ddd.domain.models.auth;

import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;

import java.util.Set;

public abstract class Auth extends ValueObject {

    public abstract String username();

    public abstract boolean hasRoles(Set<String> mandatoryRoles);

    @Override
    public String toString() {
        return username();
    }
}
