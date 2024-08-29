package com.jeferro.products.users.users.application.params;

import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.auth.Username;
import org.apache.commons.lang3.StringUtils;

public class SignInParams extends Params<User> {

    private Username username;

    private String plainPassword;

    public SignInParams(Username username, String plainPassword) {
        super();

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
