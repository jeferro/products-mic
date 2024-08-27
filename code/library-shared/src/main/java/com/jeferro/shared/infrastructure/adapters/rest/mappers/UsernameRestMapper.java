package com.jeferro.shared.infrastructure.adapters.rest.mappers;

import com.jeferro.shared.domain.models.auth.Username;
import com.jeferro.shared.infrastructure.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UsernameRestMapper extends IdentifierMapper<Username, String> {

    public static final UsernameRestMapper INSTANCE = Mappers.getMapper(UsernameRestMapper.class);
}
