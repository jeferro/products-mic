package com.jeferro.products.shared.infrastructure.adapters.rest.mappers;

import com.jeferro.products.shared.domain.models.users.Username;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UsernameRestMapper extends IdentifierMapper<Username, String> {

    public static final UsernameRestMapper INSTANCE = Mappers.getMapper(UsernameRestMapper.class);
}
