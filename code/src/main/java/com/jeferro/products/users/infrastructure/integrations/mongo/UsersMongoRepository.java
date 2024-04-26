package com.jeferro.products.users.infrastructure.integrations.mongo;

import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.domain.repositories.UsersRepository;
import com.jeferro.products.users.infrastructure.integrations.mongo.daos.UsersMongoDao;
import com.jeferro.products.users.infrastructure.integrations.mongo.mappers.UsersMongoMapper;
import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.infrastructure.integrations.mongo.mappers.UsernameMongoMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
