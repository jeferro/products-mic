package com.jeferro.products.users.application.commands;

import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.shared.application.commands.Command;
import com.jeferro.products.shared.application.commands.CommandValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.users.Username;
import org.apache.commons.lang3.StringUtils;

public class SignInCommand extends Command<User> {

    private final Username username;

    private final String plainPassword;

    public SignInCommand(Auth auth, Username username, String plainPassword) {
        super(auth);

        if (username == null) {
            throw CommandValidationException.ofMessage("Username is null");
        }

        if (StringUtils.isBlank(plainPassword)) {
            throw CommandValidationException.ofMessage("Plain password is blank");
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
