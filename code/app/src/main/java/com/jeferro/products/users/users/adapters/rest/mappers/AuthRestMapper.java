package com.jeferro.products.users.users.adapters.rest.mappers;

import com.jeferro.products.generated.rest.v1.dtos.AuthRestDTO;
import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.shared.infrastructure.mappers.ToDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	UsernameRestMapper.class
})
public abstract class AuthRestMapper extends ToDTOMapper<User, AuthRestDTO> {

	public static final AuthRestMapper INSTANCE = Mappers.getMapper(AuthRestMapper.class);

}
