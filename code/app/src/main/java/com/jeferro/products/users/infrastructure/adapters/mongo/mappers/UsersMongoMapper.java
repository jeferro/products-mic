package com.jeferro.products.users.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.mongodb.MongoProfile;
import com.jeferro.products.components.mongodb.users.dtos.UserMongoDTO;
import com.jeferro.products.shared.infrastructure.adapters.mongo.mappers.MetadataMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.mongo.mappers.UsernameMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.BidirectionalMapper;
import com.jeferro.products.users.domain.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper(uses = {
	UsernameMongoMapper.class,
	MetadataMongoMapper.class
})
@Profile(MongoProfile.NAME)
public abstract class UsersMongoMapper extends BidirectionalMapper<User, UserMongoDTO> {

    public static final UsersMongoMapper INSTANCE = Mappers.getMapper(UsersMongoMapper.class);

	@Override
	@Mapping(target = "username", source = "dto.id")
	public abstract User toDomain(UserMongoDTO dto);
}
