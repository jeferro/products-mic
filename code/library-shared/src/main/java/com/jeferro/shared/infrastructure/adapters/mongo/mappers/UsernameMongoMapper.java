package com.jeferro.shared.infrastructure.adapters.mongo.mappers;

import com.jeferro.shared.domain.models.auth.Username;
import com.jeferro.shared.infrastructure.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UsernameMongoMapper extends IdentifierMapper<Username, String> {

	public static final UsernameMongoMapper INSTANCE = Mappers.getMapper(UsernameMongoMapper.class);

}
