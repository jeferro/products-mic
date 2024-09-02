package com.jeferro.shared.auth.infrastructure.adapters.mongo.mappers;

import com.jeferro.shared.auth.domain.models.Username;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UsernameMongoMapper extends IdentifierMapper<Username, String> {

	public static final UsernameMongoMapper INSTANCE = Mappers.getMapper(UsernameMongoMapper.class);

}
