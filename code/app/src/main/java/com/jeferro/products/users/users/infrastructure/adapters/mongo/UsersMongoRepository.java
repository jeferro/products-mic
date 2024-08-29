package com.jeferro.products.users.users.infrastructure.adapters.mongo;

import java.util.Optional;

import com.jeferro.products.users.users.infrastructure.adapters.mongo.daos.UsersMongoDao;
import com.jeferro.shared.domain.models.auth.Username;
import com.jeferro.shared.infrastructure.adapters.mongo.mappers.UsernameMongoMapper;
import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.domain.repositories.UsersRepository;
import com.jeferro.products.users.users.infrastructure.adapters.mongo.mappers.UsersMongoMapper;
import org.springframework.stereotype.Component;

@Component
public class UsersMongoRepository implements UsersRepository {

    private final UsernameMongoMapper usernameMongoMapper = UsernameMongoMapper.INSTANCE;

    private final UsersMongoMapper usersMongoMapper = UsersMongoMapper.INSTANCE;

    private final UsersMongoDao usersMongoDao;

    public UsersMongoRepository(UsersMongoDao usersMongoDao) {
        this.usersMongoDao = usersMongoDao;
    }

    @Override
    public Optional<User> findById(Username username) {
        var usernameDto = usernameMongoMapper.toDTO(username);

        return usersMongoDao.findById(usernameDto)
                .map(usersMongoMapper::toDomain);
    }
}
