package com.jeferro.products.shared.domain.models.auth;

import java.util.Set;

import static com.jeferro.shared.ddd.application.Roles.ADMIN;
import static com.jeferro.shared.ddd.application.Roles.USER;

import com.jeferro.shared.auth.domain.models.AnonymousAuth;
import com.jeferro.shared.auth.domain.models.SystemAuth;
import com.jeferro.shared.auth.domain.models.UserAuth;
import com.jeferro.shared.auth.domain.models.Username;

public class AuthMother {

    public static UserAuth user() {
        var username = new Username("user");
        var roles = Set.of(USER);

        return new UserAuth(username, roles);
    }

    public static UserAuth admin() {
        var username = new Username("admin");
        var roles = Set.of(ADMIN);

        return new UserAuth(username, roles);
    }

    public static AnonymousAuth anonymous() {
        return new AnonymousAuth();
    }

    public static SystemAuth system() {
        return new SystemAuth();
    }

}