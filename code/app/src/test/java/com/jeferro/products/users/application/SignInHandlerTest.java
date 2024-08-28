package com.jeferro.products.users.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jeferro.shared.domain.exceptions.UnauthorizedException;
import com.jeferro.products.shared.domain.models.auth.AuthMother;
import com.jeferro.products.users.application.params.SignInParams;
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

	var anonymousAuth = AuthMother.anonymous();
	var params = new SignInParams(
		user.getUsername(),
		user.getEncodedPassword()
	);

	var result = signInHandler.execute(anonymousAuth, params);

	assertEquals(user, result);
  }

  @Test
  void givenAnAuthenticatedUser_whenSignIn_thenReturnsUser() {
	var user = givenAnUserInDatabase();
	var userAuth = AuthMother.user();

	var params = new SignInParams(
		user.getUsername(),
		user.getEncodedPassword()
	);

	var result = signInHandler.execute(userAuth, params);

	assertEquals(user, result);
  }

  @Test
  void givenNoUsers_whenSignIn_thenThrowsUnauthorizedException() {
	var user = UserMother.user();
	var anonymousAuth = AuthMother.anonymous();

	var params = new SignInParams(
		user.getUsername(),
		user.getEncodedPassword()
	);

	assertThrows(UnauthorizedException.class,
		() -> signInHandler.execute(anonymousAuth, params));
  }

  @Test
  void givenWrongCredentials_whenSignIn_thenThrowsUnauthorizedException() {
	var user = givenAnUserInDatabase();
	var anonymousAuth = AuthMother.anonymous();

	var params = new SignInParams(
		user.getUsername(),
		"wrong-password"
	);

	assertThrows(UnauthorizedException.class,
		() -> signInHandler.execute(anonymousAuth, params));
  }

  private User givenAnUserInDatabase() {
	var user = UserMother.user();
	usersInMemoryRepository.init(user);
	return user;
  }
}