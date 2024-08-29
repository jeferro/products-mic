package com.jeferro.products.users.users.domain.services;

import com.jeferro.products.users.users.domain.services.PasswordEncoder;

public class FakePasswordEncoder implements PasswordEncoder {

    @Override
    public boolean matches(String plainPassword, String encodedPassword) {
        return plainPassword.equals(encodedPassword);
    }
}