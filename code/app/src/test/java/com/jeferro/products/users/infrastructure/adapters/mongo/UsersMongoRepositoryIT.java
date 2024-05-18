package com.jeferro.products.users.infrastructure.adapters.mongo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import com.jeferro.products.components.mongodb.users.UsersMongoDao;
import com.jeferro.products.shared.infrastructure.adapters.mongo.MongoRepositoryIT;
import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.domain.models.UserMother;
import com.jeferro.products.users.infrastructure.adapters.mongo.mappers.UsersMongoMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
/* TODO
@Import(UsersMongoRepository.class)
class UsersMongoRepositoryIT extends MongoRepositoryIT{

    private final UsersMongoMapper usersMongoMapper = UsersMongoMapper.INSTANCE;

    @Autowired
    private UsersMongoDao usersMongoDao;

    @Autowired
    private UsersMongoRepository usersMongoRepository;

    @Nested
    class FindByIdTests {

        @Test
        void givenOneUser_whenFindById_thenReturnsUser() {
            var expected = UserMother.user();
            givenUsersInDatabase(expected);

            var result = usersMongoRepository.findById(expected.getUsername());

            assertTrue(result.isPresent());
            assertEquals(expected, result.get());
        }

        @Test
        void givenNoUsers_whenFindById_thenReturnsEmpty() {
            givenDatabaseIsEmpty();

            var expected = UserMother.user();
            var result = usersMongoRepository.findById(expected.getUsername());

            assertTrue(result.isEmpty());
        }
    }

    private void givenDatabaseIsEmpty() {
        usersMongoDao.deleteAll();
    }

    private void givenUsersInDatabase(User... users) {
        usersMongoDao.deleteAll();

        Arrays.stream(users)
                .map(usersMongoMapper::toDTO)
                .forEach(usersMongoDao::save);
    }
}
*/