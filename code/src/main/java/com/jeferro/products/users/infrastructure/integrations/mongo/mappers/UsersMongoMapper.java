package com.jeferro.products.users.infrastructure.integrations.mongo.mappers;

import com.jeferro.products.shared.infrastructure.integrations.mappers.BidirectionalMapper;
import com.jeferro.products.shared.infrastructure.integrations.mongo.mappers.MetadataMongoMapper;
import com.jeferro.products.shared.infrastructure.integrations.mongo.mappers.UsernameMongoMapper;
import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.infrastructure.integrations.mongo.dtos.UserMongoDTO;
import org.springframework.stereotype.Component;

@Component
public class UsersMongoMapper extends BidirectionalMapper<User, UserMongoDTO> {

    public static final UsersMongoMapper INSTANCE = new UsersMongoMapper();

    private final MetadataMongoMapper metadataMongoMapper = MetadataMongoMapper.INSTANCE;

    private final UsernameMongoMapper usernameMongoMapper = UsernameMongoMapper.INSTANCE;

    @Override
    public User toDomain(UserMongoDTO dto) {
        return new User(
                usernameMongoMapper.toDomain(dto.id()),
                dto.encodedPassword(),
                dto.roles(),
                metadataMongoMapper.toDomain(dto.metadata())
        );
    }

    @Override
    public UserMongoDTO toDTO(User user) {
        return new UserMongoDTO(
                usernameMongoMapper.toDTO(user.getId()),
                user.getEncodedPassword(),
                user.getRoles(),
                metadataMongoMapper.toDTO(user.getMetadata())
        );
    }
}
