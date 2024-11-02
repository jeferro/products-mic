package com.jeferro.products.users.users.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jeferro.products.shared.application.ContextMother;
import com.jeferro.products.users.users.application.params.SignInParams;
import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.domain.models.UserMother;
import com.jeferro.products.users.users.domain.repositories.UsersInMemoryRepository;
import com.jeferro.products.users.users.domain.services.FakePasswordEncoder;
import com.jeferro.shared.ddd.domain.exceptions.auth.UnauthorizedException;
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

	var anonymousContext = ContextMother.anonymous();
	var params = new SignInParams(
		user.getUsername(),
		user.getEncodedPassword()
	);

	var result = signInHandler.execute(anonymousContext, params);

	assertEquals(user, result);
  }

  @Test
  void givenAnAuthenticatedUser_whenSignIn_thenReturnsUser() {
	var user = givenAnUserInDatabase();
	var userContext = ContextMother.user();

	var params = new SignInParams(
		user.getUsername(),
		user.getEncodedPassword()
	);

	var result = signInHandler.execute(userContext, params);

	assertEquals(user, result);
  }

  @Test
  void givenNoUsers_whenSignIn_thenThrowsUnauthorizedException() {
	var user = UserMother.user();
	var anonymousContext = ContextMother.anonymous();

	var params = new SignInParams(
		user.getUsername(),
		user.getEncodedPassword()
	);

	assertThrows(UnauthorizedException.class,
		() -> signInHandler.execute(anonymousContext, params));
  }

  @Test
  void givenWrongCredentials_whenSignIn_thenThrowsUnauthorizedException() {
	var user = givenAnUserInDatabase();
	var anonymousContext = ContextMother.anonymous();

	var params = new SignInParams(
		user.getUsername(),
		"wrong-password"
	);

	assertThrows(UnauthorizedException.class,
		() -> signInHandler.execute(anonymousContext, params));
  }

  private User givenAnUserInDatabase() {
	var user = UserMother.user();
	usersInMemoryRepository.init(user);
	return user;
  }
}