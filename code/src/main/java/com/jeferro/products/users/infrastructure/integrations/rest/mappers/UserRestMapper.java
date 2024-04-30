package com.jeferro.products.users.infrastructure.integrations.rest.mappers;

import com.jeferro.products.shared.infrastructure.integrations.mappers.ToDTOMapper;
import com.jeferro.products.shared.infrastructure.integrations.rest.mappers.UsernameRestMapper;
import com.jeferro.products.users.domain.models.User;
import com.jeferro.products.users.infrastructure.integrations.rest.dtos.UserRestDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRestMapper extends ToDTOMapper<User, UserRestDTO> {

    public static final UserRestMapper INSTANCE = new UserRestMapper();

    private final UsernameRestMapper usernameRestMapper = UsernameRestMapper.INSTANCE;

    @Override
    public UserRestDTO toDTO(User user) {
        return new UserRestDTO(
                usernameRestMapper.toDTO(user.getId()),
                user.getRoles()
        );
    }
}
