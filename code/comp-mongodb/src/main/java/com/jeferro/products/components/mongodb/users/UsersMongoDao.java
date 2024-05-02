package com.jeferro.products.components.mongodb.users;

import com.jeferro.products.components.mongodb.MongoProfile;
import com.jeferro.products.components.mongodb.users.dtos.UserMongoDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile(MongoProfile.NAME)
public interface UsersMongoDao extends MongoRepository<UserMongoDTO, String> {


}
