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

		validateUsername(username);
		validatePlainPassword(plainPassword);

		this.username = username;
        this.plainPassword = plainPassword;
    }

	public Username getUsername() {
        return username;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

	private static void validateUsername(Username username) {
		if (username == null) {
			throw ValueValidationException.createOfMessage("Username is null");
		}
	}

	private static void validatePlainPassword(String plainPassword) {
		if (StringUtils.isBlank(plainPassword)) {
			throw ValueValidationException.createOfMessage("Plain password is blank");
		}
	}
}
