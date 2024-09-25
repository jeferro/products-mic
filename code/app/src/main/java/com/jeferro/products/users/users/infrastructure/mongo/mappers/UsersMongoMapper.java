package com.jeferro.products.users.users.infrastructure.mongo.mappers;

import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.infrastructure.mongo.dtos.UserMongoDTO;
import com.jeferro.shared.auth.domain.models.Username;
import com.jeferro.shared.mappers.MapstructConfig;
import com.jeferro.shared.mappers.SecondaryAggregateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public abstract class UsersMongoMapper extends SecondaryAggregateMapper<User, Username, UserMongoDTO> {

    public static final UsersMongoMapper INSTANCE = Mappers.getMapper(UsersMongoMapper.class);
}
