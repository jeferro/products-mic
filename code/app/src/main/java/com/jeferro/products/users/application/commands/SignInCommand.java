package com.jeferro.products.users.application.commands;

import com.jeferro.shared.application.commands.Command;
import com.jeferro.products.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.auth.Auth;
import com.jeferro.shared.domain.models.auth.Username;
import com.jeferro.products.users.domain.models.User;
import org.apache.commons.lang3.StringUtils;

public class SignInCommand extends Command<User> {

    private Username username;

    private String plainPassword;

    public SignInCommand(Auth auth, Username username, String plainPassword) {
        super(auth);

		setUsername(username);
		setPlainPassword(plainPassword);
    }

	public Username getUsername() {
        return username;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

	private void setUsername(Username username) {
		if (username == null) {
			throw ValueValidationException.createOfMessage("Username is null");
		}

	  this.username = username;
	}

	private void setPlainPassword(String plainPassword) {
		if (StringUtils.isBlank(plainPassword)) {
			throw ValueValidationException.createOfMessage("Plain password is blank");
		}

	  this.plainPassword = plainPassword;
	}
}
