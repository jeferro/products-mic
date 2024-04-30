package com.jeferro.products.shared.domain.models.auth;

import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.domain.models.value_objects.ValueObject;

import java.util.Set;

public abstract class Auth extends ValueObject {

    public abstract String who();

    public abstract boolean hasPermissions(Set<String> mandatoryRoles);

    @Override
    public String toString() {
        return who();
    }

    public abstract boolean belongsToUser(Username username);

    public abstract boolean isAnonymous();

    public abstract boolean isSystem();
}
