package com.jeferro.products.users.users.domain.repositories;

import com.jeferro.products.shared.domain.repositories.InMemoryRepository;
import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.domain.models.Username;

public class UsersInMemoryRepository extends InMemoryRepository<User, Username>
        implements UsersRepository {
}