package com.jeferro.products.users.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.mongodb.MongoProfile;
import com.jeferro.products.components.mongodb.users.dtos.UserMongoDTO;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.BidirectionalMapper;
import com.jeferro.products.shared.infrastructure.adapters.mongo.mappers.UsernameMongoMapper;
import com.jeferro.products.users.domain.models.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(MongoProfile.NAME)
public class UsersMongoMapper extends BidirectionalMapper<User, UserMongoDTO> {

    public static final UsersMongoMapper INSTANCE = new UsersMongoMapper();

    private final UsernameMongoMapper usernameMongoMapper = UsernameMongoMapper.INSTANCE;

    @Override
    public User toDomain(UserMongoDTO dto) {
        return new User(
                usernameMongoMapper.toDomain(dto.id()),
                dto.encodedPassword(),
                dto.roles()
        );
    }

    @Override
    public UserMongoDTO toDTO(User user) {
        return new UserMongoDTO(
                usernameMongoMapper.toDTO(user.getId()),
                user.getEncodedPassword(),
                user.getRoles()
        );
    }
}
