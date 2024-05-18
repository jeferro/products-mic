package com.jeferro.products.users.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jeferro.products.shared.domain.exceptions.UnauthorizedException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.users.application.commands.SignInCommand;
import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.domain.models.UserMother;
import com.jeferro.products.users.domain.repositories.UsersInMemoryRepository;
import com.jeferro.products.users.domain.services.FakePasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignInHandlerTest {

  private UsersInMemoryRepository usersInMemoryRepository;

  private SignInHandler signInHandler;

  @BeforeEach
  void beforeEach() {
	usersInMemoryRepository = new UsersInMemoryRepository();
	var fakePasswordEncoder = new FakePasswordEncoder();

	signInHandler = new SignInHandler(usersInMemoryRepository, fakePasswordEncoder);
  }

  @Test
  void givenOneUSer_whenSignIn_thenReturnsUser() {
	var user = givenAnUserInDatabase();

	var command = new SignInCommand(
		AuthMother.anonymous(),
		user.getUsername(),
		user.getEncodedPassword()
	);

	var result = signInHandler.execute(command);

	assertEquals(user, result);
  }

  @Test
  void givenAnAuthenticatedUser_whenSignIn_thenReturnsUser() {
	var user = givenAnUserInDatabase();

	var command = new SignInCommand(
		AuthMother.user(),
		user.getUsername(),
		user.getEncodedPassword()
	);

	var result = signInHandler.execute(command);

	assertEquals(user, result);
  }

  @Test
  void givenNoUsers_whenSignIn_thenThrowsUnauthorizedException() {
	var user = UserMother.user();

	var command = new SignInCommand(
		AuthMother.anonymous(),
		user.getUsername(),
		user.getEncodedPassword()
	);

	assertThrows(UnauthorizedException.class,
		() -> signInHandler.execute(command));
  }

  @Test
  void givenWrongCredentials_whenSignIn_thenThrowsUnauthorizedException() {
	var user = givenAnUserInDatabase();

	var command = new SignInCommand(
		AuthMother.anonymous(),
		user.getUsername(),
		"wrong-password"
	);

	assertThrows(UnauthorizedException.class,
		() -> signInHandler.execute(command));
  }

  private User givenAnUserInDatabase() {
	var user = UserMother.user();
	usersInMemoryRepository.init(user);
	return user;
  }
}