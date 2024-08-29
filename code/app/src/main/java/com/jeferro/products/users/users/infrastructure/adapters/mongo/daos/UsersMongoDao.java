package com.jeferro.products.users.users.infrastructure.adapters.mongo.daos;

import com.jeferro.products.users.users.infrastructure.adapters.mongo.dtos.UserMongoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMongoDao extends MongoRepository<UserMongoDTO, String> {


}
