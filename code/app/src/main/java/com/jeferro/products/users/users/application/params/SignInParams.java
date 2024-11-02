package com.jeferro.products.users.users.application.params;

import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.models.auth.Username;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;

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
	  ValueValidationUtils.isNotNull(username, "Username");
	  this.username = username;
	}

	private void setPassword(String password) {
	  ValueValidationUtils.isNotBlank(password, "Plain password");
	  this.password = password;
	}
}
