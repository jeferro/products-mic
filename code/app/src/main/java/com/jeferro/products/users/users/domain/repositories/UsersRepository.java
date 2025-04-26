package com.jeferro.products.users.users.domain.repositories;

import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.domain.models.Username;

import java.util.Optional;

public interface UsersRepository {

    Optional<User> findById(Username username);
}
