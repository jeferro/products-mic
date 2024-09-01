package com.jeferro.shared.auth.infrastructure.adapters.rest.mappers;

import com.jeferro.shared.auth.domain.models.Username;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UsernameRestMapper extends IdentifierMapper<Username, String> {

    public static final UsernameRestMapper INSTANCE = Mappers.getMapper(UsernameRestMapper.class);
}
