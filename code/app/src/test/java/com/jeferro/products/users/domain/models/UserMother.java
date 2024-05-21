package com.jeferro.products.users.domain.models;

import java.util.Set;

import com.jeferro.products.shared.application.Roles;
import com.jeferro.products.shared.domain.models.users.Username;

public class UserMother {

    public static User user() {
        var username = new Username("user");
        var roles = Set.of(Roles.USER);

        return new User(username, "encoded-password", roles);
    }

    public static User admin() {
        var admin = new Username("admin");
        var roles = Set.of(Roles.USER, Roles.ADMIN);

        return new User(admin, "encoded-password", roles);
    }
}