package com.jeferro.products.users.domain.models;

import com.jeferro.products.shared.application.Roles;
import com.jeferro.products.shared.domain.models.metadata.Metadata;
import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.domain.services.time.TimeService;

import java.util.Set;

public class UserMother {

    public static User user() {
        var username = new Username("user-one");
        var roles = Set.of(Roles.USER);

        var now = TimeService.now();
        var metadata = new Metadata(now, "user-one", now, "user-one");

        return new User(username, "encoded-password", roles, metadata);
    }
}