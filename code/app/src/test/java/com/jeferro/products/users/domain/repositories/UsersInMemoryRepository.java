package com.jeferro.products.users.domain.repositories;

import com.jeferro.shared.domain.models.auth.Username;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;
import com.jeferro.products.users.domain.models.User;

public class UsersInMemoryRepository extends InMemoryRepository<User, Username>
        implements UsersRepository {
}