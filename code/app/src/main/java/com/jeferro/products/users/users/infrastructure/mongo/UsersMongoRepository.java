package com.jeferro.products.users.users.infrastructure.mongo;

import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.domain.models.Username;
import com.jeferro.products.users.users.domain.repositories.UsersRepository;
import com.jeferro.products.users.users.infrastructure.mongo.daos.UsersMongoDao;
import com.jeferro.products.users.users.infrastructure.mongo.mappers.UsersMongoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsersMongoRepository implements UsersRepository {

    private final UsersMongoMapper usersMongoMapper = UsersMongoMapper.INSTANCE;

    private final UsersMongoDao usersMongoDao;

    @Override
    public Optional<User> findById(Username username) {
        var usernameDto = usersMongoMapper.toDTO(username);

        return usersMongoDao.findById(usernameDto)
                .map(usersMongoMapper::toDomain);
    }
}
