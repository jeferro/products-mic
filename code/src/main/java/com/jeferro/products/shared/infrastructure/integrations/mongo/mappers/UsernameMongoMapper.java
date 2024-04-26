package com.jeferro.products.shared.infrastructure.integrations.mongo.mappers;

import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.infrastructure.integrations.mappers.SimpleIdentifierMapper;

public class UsernameMongoMapper extends SimpleIdentifierMapper<Username, String> {

    public static final UsernameMongoMapper INSTANCE = new UsernameMongoMapper();

    @Override
    public Username toDomain(String dto) {
        return new Username(dto);
    }
}
