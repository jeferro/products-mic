package com.jeferro.products.users.users.infrastructure.rest.mappers;

import com.jeferro.products.users.users.application.params.SignInParams;
import com.jeferro.products.users.users.domain.models.User;
import com.jeferro.products.users.users.infrastructure.rest.dtos.AuthRestDTO;
import com.jeferro.products.users.users.infrastructure.rest.dtos.SignInInputRestDTO;
import com.jeferro.shared.mappers.MapstructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public abstract class AuthRestMapper {

  public static final AuthRestMapper INSTANCE = Mappers.getMapper(AuthRestMapper.class);

  public abstract SignInParams toSignInParams(SignInInputRestDTO inputRestDTO);

  public abstract AuthRestDTO toDTO(User user);
}
