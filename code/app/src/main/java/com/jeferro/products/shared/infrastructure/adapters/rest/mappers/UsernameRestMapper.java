package com.jeferro.products.shared.infrastructure.adapters.rest.mappers;

import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.springframework.context.annotation.Profile;

@Profile(RestProfile.NAME)
public class UsernameRestMapper extends IdentifierMapper<Username, String> {

    public static final UsernameRestMapper INSTANCE = new UsernameRestMapper();

    @Override
    public Username toDomain(String dto) {
        return new Username(dto);
    }
}
