package com.jeferro.products.shared.domain.models.auth;

import com.jeferro.shared.ddd.domain.models.auth.AnonymousAuth;
import com.jeferro.shared.ddd.domain.models.auth.SystemAuth;
import com.jeferro.shared.ddd.domain.models.auth.UserAuth;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.ADMIN;
import static com.jeferro.products.shared.application.Roles.USER;

public class AuthMother {

    public static UserAuth user() {
        var roles = Set.of(USER);

        return new UserAuth("user", roles);
    }

    public static UserAuth admin() {
        var roles = Set.of(ADMIN);

        return new UserAuth("admin", roles);
    }

    public static AnonymousAuth anonymous() {
        return new AnonymousAuth();
    }

    public static SystemAuth system() {
        return new SystemAuth();
    }

}