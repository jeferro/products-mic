package com.jeferro.products.users.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.mongodb.users.dtos.UserMongoDTO;
import com.jeferro.products.shared.infrastructure.adapters.mongo.mappers.UsernameMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.BidirectionalMapper;
import com.jeferro.products.users.domain.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	UsernameMongoMapper.class
})
public abstract class UsersMongoMapper extends BidirectionalMapper<User, UserMongoDTO> {

    public static final UsersMongoMapper INSTANCE = Mappers.getMapper(UsersMongoMapper.class);

	@Override
	@Mapping(target = "username", source = "dto.id")
	public abstract User toDomain(UserMongoDTO dto);
}
