package com.jeferro.products.users.application;

import com.jeferro.products.shared.application.Command;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.users.domain.models.User;
import org.apache.commons.lang3.StringUtils;

public class SignInCommand extends Command<User> {

    private final Username username;

    private final String plainPassword;

    public SignInCommand(Auth auth, Username username, String plainPassword) {
        super(auth);

        if (username == null) {
            throw ValueValidationException.ofMessage("Username is null");
        }

        if (StringUtils.isBlank(plainPassword)) {
            throw ValueValidationException.ofMessage("Plain password is blank");
        }

        this.username = username;
        this.plainPassword = plainPassword;
    }

    public Username getUsername() {
        return username;
    }

    public String getPlainPassword() {
        return plainPassword;
    }
}
