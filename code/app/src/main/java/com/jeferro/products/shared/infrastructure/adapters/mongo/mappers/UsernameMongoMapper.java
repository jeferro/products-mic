package com.jeferro.products.shared.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper
@Profile(RestProfile.NAME)
public abstract class UsernameMongoMapper extends IdentifierMapper<Username, String> {

	public static final UsernameMongoMapper INSTANCE = Mappers.getMapper(UsernameMongoMapper.class);

}
