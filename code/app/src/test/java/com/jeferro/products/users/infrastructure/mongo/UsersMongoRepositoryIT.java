package com.jeferro.products.users.infrastructure.mongo;

import com.jeferro.products.shared.infrastructure.mongo.MongoDBContainerCreator;
import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.domain.models.UserMother;
import com.jeferro.products.users.infrastructure.adapters.mongo.UsersMongoRepository;
import com.jeferro.products.components.mongodb.users.UsersMongoDao;
import com.jeferro.products.users.infrastructure.adapters.mongo.mappers.UsersMongoMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@DataMongoTest
@Import(UsersMongoRepository.class)
class UsersMongoRepositoryIT {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = MongoDBContainerCreator.create();

    private final UsersMongoMapper usersMongoMapper = UsersMongoMapper.INSTANCE;

    @Autowired
    private UsersMongoDao usersMongoDao;

    @Autowired
    private UsersMongoRepository usersMongoRepository;

    @Nested
    class FindByIdTests {

        @Test
        void should_retrieveUser_when_userExists() {
            var expected = UserMother.user();
            givenUsersInDatabase(expected);

            var result = usersMongoRepository.findById(expected.getUsername());

            assertTrue(result.isPresent());
            assertEquals(expected, result.get());
        }

        @Test
        void should_retrieveEmpty_when_userDoesNotExist() {
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
