package com.jeferro.products.components.mongodb.users;

import com.jeferro.products.components.mongodb.users.dtos.UserMongoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMongoDao extends MongoRepository<UserMongoDTO, String> {


}
