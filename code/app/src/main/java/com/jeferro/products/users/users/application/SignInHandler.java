package com.jeferro.products.users.users.application;

import java.util.Set;

import com.jeferro.products.users.users.application.params.SignInParams;
import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.domain.repositories.UsersRepository;
import com.jeferro.products.users.users.domain.services.PasswordEncoder;
import com.jeferro.shared.ddd.application.Context;
import com.jeferro.shared.ddd.application.handlers.Handler;
import com.jeferro.shared.ddd.domain.exceptions.UnauthorizedException;
import com.jeferro.shared.auth.domain.models.Username;
import org.springframework.stereotype.Component;

@Component
public class SignInHandler extends Handler<SignInParams, User> {

  private final UsersRepository usersRepository;

  private final PasswordEncoder passwordEncoder;

  public SignInHandler(UsersRepository usersRepository,
	  PasswordEncoder passwordEncoder) {
	super();

	this.usersRepository = usersRepository;
	this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Set<String> getMandatoryUserRoles() {
	return Set.of();
  }

  @Override
  public User execute(Context context, SignInParams params) {
	var username = params.getUsername();
	var plainPassword = params.getPassword();

	var user = ensureUserExists(username);

	ensurePasswordIsCorrect(plainPassword, user);

	return user;
  }

  private User ensureUserExists(Username username) {
	return usersRepository.findById(username)
		.orElseThrow(UnauthorizedException::createOf);
  }

  private void ensurePasswordIsCorrect(String plainPassword, User user) {
	var matches = passwordEncoder.matches(plainPassword, user.getEncodedPassword());

	if (!matches) {
	  throw UnauthorizedException.createOf();
	}
  }
}
