package com.jeferro.products.products.infrastructure.adapters.rest.mappers;

import com.jeferro.products.components.rest.shared.RestProfile;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper
@Profile(RestProfile.NAME)
public abstract class ProductIdRestMapper extends IdentifierMapper<ProductId, String> {

    public static final ProductIdRestMapper INSTANCE = Mappers.getMapper(ProductIdRestMapper.class);
}
