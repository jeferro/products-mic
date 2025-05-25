package com.jeferro.products.users.users.application;

import com.jeferro.products.shared.application.ContextMother;
import com.jeferro.products.users.users.application.params.SignInParams;
import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.domain.models.UserMother;
import com.jeferro.products.users.users.domain.repositories.UsersInMemoryRepository;
import com.jeferro.products.users.users.domain.services.FakePasswordEncoder;
import com.jeferro.shared.ddd.domain.exceptions.auth.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SignInUseCaseTest {

    private UsersInMemoryRepository usersInMemoryRepository;

    private SignInUseCase signInUseCase;

    @BeforeEach
    void beforeEach() {
        usersInMemoryRepository = new UsersInMemoryRepository();
        var fakePasswordEncoder = new FakePasswordEncoder();

        signInUseCase = new SignInUseCase(usersInMemoryRepository, fakePasswordEncoder);
    }

    @Test
    void givenOneUSer_whenSignIn_thenReturnsUser() {
        var user = givenAnUserInDatabase();

        var anonymousContext = ContextMother.anonymous();
        var params = new SignInParams(
                user.getUsername(),
                user.getEncodedPassword()
        );

        var result = signInUseCase.execute(anonymousContext, params);

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

        var result = signInUseCase.execute(userContext, params);

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
                () -> signInUseCase.execute(anonymousContext, params));
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
                () -> signInUseCase.execute(anonymousContext, params));
    }

    private User givenAnUserInDatabase() {
        var user = UserMother.user();
        usersInMemoryRepository.init(user);
        return user;
    }
}
