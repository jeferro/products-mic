package com.jeferro.products.users.users.domain.models;

import com.jeferro.products.shared.application.Roles;
import com.jeferro.products.shared.domain.models.auth.UsernameMother;

import java.util.Set;

public class UserMother {

    public static User user() {
        var username = UsernameMother.user();
        var roles = Set.of(Roles.USER);

        return new User(username, "encoded-password", roles);
    }

    public static User admin() {
        var username = UsernameMother.admin();
        var roles = Set.of(Roles.USER, Roles.ADMIN);

        return new User(username, "encoded-password", roles);
    }
}