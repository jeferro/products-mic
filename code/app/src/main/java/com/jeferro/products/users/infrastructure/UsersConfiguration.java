package com.jeferro.products.users.infrastructure;

import com.jeferro.products.users.application.SignInHandler;
import com.jeferro.products.users.domain.repositories.UsersRepository;
import com.jeferro.products.users.domain.services.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersConfiguration {

    @Bean
    public SignInHandler signInHandler(UsersRepository usersRepository,
                                       PasswordEncoder passwordEncoder) {
        return new SignInHandler(usersRepository, passwordEncoder);
    }
}
