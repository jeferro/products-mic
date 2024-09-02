package com.jeferro.products.users.users.domain.repositories;

import com.jeferro.shared.auth.domain.models.Username;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;
import com.jeferro.products.users.users.domain.models.User;

public class UsersInMemoryRepository extends InMemoryRepository<User, Username>
        implements UsersRepository {
}