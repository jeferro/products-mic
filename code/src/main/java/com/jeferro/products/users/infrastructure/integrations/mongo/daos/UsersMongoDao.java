package com.jeferro.products.users.infrastructure.integrations.mongo.daos;

import com.jeferro.products.users.infrastructure.integrations.mongo.dtos.UserMongoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMongoDao extends MongoRepository<UserMongoDTO, String> {

}
