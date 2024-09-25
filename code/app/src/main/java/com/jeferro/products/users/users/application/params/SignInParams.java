package com.jeferro.products.users.users.application.params;

import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.auth.domain.models.Username;
import org.apache.commons.lang3.StringUtils;

public class SignInParams extends Params<User> {

    private Username username;

    private String password;

    public SignInParams(Username username, String password) {
        super();

		setUsername(username);
		setPassword(password);
    }

	public Username getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

	private void setUsername(Username username) {
		if (username == null) {
			throw ValueValidationException.createOfMessage("Username is null");
		}

	  this.username = username;
	}

	private void setPassword(String password) {
		if (StringUtils.isBlank(password)) {
			throw ValueValidationException.createOfMessage("Plain password is blank");
		}

	  this.password = password;
	}
}
