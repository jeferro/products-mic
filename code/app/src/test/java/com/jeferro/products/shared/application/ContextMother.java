package com.jeferro.products.shared.application;

import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.shared.ddd.domain.models.context.Context;

import java.util.Locale;

public class ContextMother {

    public static Context user() {
        var auth = AuthMother.user();
        var locale = Locale.of("en", "US");

        return new Context(auth, locale);
    }

    public static Context admin() {
        var auth = AuthMother.admin();
        var locale = Locale.of("en", "US");

        return new Context(auth, locale);
    }

    public static Context anonymous() {
        var auth = AuthMother.anonymous();
        var locale = Locale.of("en", "US");

        return new Context(auth, locale);
    }
}