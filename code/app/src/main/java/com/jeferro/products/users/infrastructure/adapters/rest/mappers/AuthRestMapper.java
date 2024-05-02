package com.jeferro.products.users.infrastructure.adapters.rest.mappers;

import com.jeferro.products.components.products.rest.dtos.AuthRestDTO;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.ToDTOMapper;
import com.jeferro.products.shared.infrastructure.adapters.rest.mappers.UsernameRestMapper;
import com.jeferro.products.users.domain.models.User;
import org.springframework.stereotype.Component;

@Component
public class AuthRestMapper extends ToDTOMapper<User, AuthRestDTO> {

	public static final AuthRestMapper INSTANCE = new AuthRestMapper();

	private final UsernameRestMapper usernameRestMapper = UsernameRestMapper.INSTANCE;

	@Override
	public AuthRestDTO toDTO(User user) {
		return new AuthRestDTO(
			usernameRestMapper.toDTO(user.getId()),
			user.getRoles().stream().toList()
		);
	}
}
