package com.jeferro.products.shared.infrastructure.integrations.rest.mappers;

import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.infrastructure.integrations.mappers.IdentifierMapper;

public class UsernameRestMapper extends IdentifierMapper<Username, String> {

    public static final UsernameRestMapper INSTANCE = new UsernameRestMapper();

    @Override
    public Username toDomain(String dto) {
        return new Username(dto);
    }
}
