package com.jeferro.products.users.users.adapters.mongo.mappers;

import com.jeferro.products.users.users.adapters.mongo.dtos.UserMongoDTO;
import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.shared.infrastructure.adapters.mongo.mappers.UsernameMongoMapper;
import com.jeferro.shared.infrastructure.mappers.BidirectionalMapper;
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
