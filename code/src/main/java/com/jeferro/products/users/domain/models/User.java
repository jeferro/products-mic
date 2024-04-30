package com.jeferro.products.users.domain.models;

import java.util.Set;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.entities.AggregateRoot;
import com.jeferro.products.shared.domain.models.users.Username;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class User extends AggregateRoot<Username> {

    private final String encodedPassword;

    private final Set<String> roles;

    public User(Username username, String encodedPassword, Set<String> roles) {
        super(username);

        validateEncodedPassword(encodedPassword);
        validateRoles(roles);

        this.encodedPassword = encodedPassword;
        this.roles = roles;
    }

    private static void validateRoles(Set<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            throw ValueValidationException.ofMessage("Roles is empty");
        }
    }

    private static void validateEncodedPassword(String encodedPassword) {
        if (StringUtils.isBlank(encodedPassword)) {
            throw ValueValidationException.ofMessage("Encoded password is blank");
        }
    }

    public Username getUsername() {
        return id;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
