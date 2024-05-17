package com.jeferro.products.shared.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UsernameMongoMapper extends IdentifierMapper<Username, String> {

	public static final UsernameMongoMapper INSTANCE = Mappers.getMapper(UsernameMongoMapper.class);

}
