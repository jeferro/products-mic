package com.jeferro.products.users.infrastructure.adapters.rest.mappers;

import com.jeferro.products.components.rest.generated.dtos.AuthRestDTO;
import com.jeferro.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.shared.infrastructure.mappers.ToDTOMapper;
import com.jeferro.products.users.domain.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	UsernameRestMapper.class
})
public abstract class AuthRestMapper extends ToDTOMapper<User, AuthRestDTO> {

	public static final AuthRestMapper INSTANCE = Mappers.getMapper(AuthRestMapper.class);

}
