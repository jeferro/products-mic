package com.jeferro.products.users.infrastructure.integrations.bcrypt;

import com.jeferro.products.users.domain.services.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FakePasswordEncoder implements PasswordEncoder {

    @Override
    public boolean matches(String plainPassword, String encodedPassword) {
        // TODO: Encrypt password
        return plainPassword.equals(encodedPassword);
    }
}
