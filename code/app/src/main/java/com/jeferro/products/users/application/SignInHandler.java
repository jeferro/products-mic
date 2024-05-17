package com.jeferro.products.users.application;

import com.jeferro.products.shared.application.Handler;
import com.jeferro.products.shared.domain.exceptions.UnauthorizedException;
import com.jeferro.products.users.application.commands.SignInCommand;
import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.domain.repositories.UsersRepository;
import com.jeferro.products.users.domain.services.PasswordEncoder;

import java.util.Set;

public class SignInHandler extends Handler<SignInCommand, User> {

    private static final Set<String> MANDATORY_ROLES = Set.of();

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public SignInHandler(UsersRepository usersRepository,
                         PasswordEncoder passwordEncoder) {
        super(MANDATORY_ROLES);

        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected User handle(SignInCommand command) {
        var username = command.getUsername();
        var plainPassword = command.getPlainPassword();

        var user = usersRepository.findById(username)
                .orElseThrow(UnauthorizedException::createOf);

        var matches = passwordEncoder.matches(plainPassword, user.getEncodedPassword());

        if (!matches) {
            throw UnauthorizedException.createOf();
        }

        return user;
    }
}
