package com.jeferro.products.users.domain.repositories;

import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;
import com.jeferro.products.users.domain.models.User;

public class UsersInMemoryRepository extends InMemoryRepository<User, Username>
        implements UsersRepository {
}