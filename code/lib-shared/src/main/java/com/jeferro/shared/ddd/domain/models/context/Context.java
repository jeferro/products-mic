package com.jeferro.shared.ddd.domain.models.context;

import com.jeferro.shared.ddd.domain.models.auth.Auth;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

import java.util.Locale;

@Getter
public class Context {

    private final Auth auth;

    private final Locale locale;

    public Context(Auth auth,
                   Locale locale) {

        this.auth = auth;
        this.locale = locale;
    }

    public static Context createOf(Auth auth, Locale locale) {
        ValueValidationUtils.isNotNull(auth, "auth", Context.class);
        ValueValidationUtils.isNotNull(locale, "locale", Context.class);

        return new Context(auth, locale);
    }
}
