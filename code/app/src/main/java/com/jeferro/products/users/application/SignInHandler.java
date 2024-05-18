package com.jeferro.products.users.application;

import java.util.Set;

import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.exceptions.UnauthorizedException;
import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.users.application.commands.SignInCommand;
import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.domain.repositories.UsersRepository;
import com.jeferro.products.users.domain.services.PasswordEncoder;

public class SignInHandler extends Handler<SignInCommand, User> {

  private final UsersRepository usersRepository;

  private final PasswordEncoder passwordEncoder;

  public SignInHandler(UsersRepository usersRepository,
	  PasswordEncoder passwordEncoder) {
	super();

	this.usersRepository = usersRepository;
	this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected Set<String> getMandatoryRoles() {
	return Set.of();
  }

  @Override
  protected User handle(SignInCommand command) {
	var username = command.getUsername();
	var plainPassword = command.getPlainPassword();

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
