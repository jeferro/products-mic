package com.jeferro.products.products.infrastructure.adapters.rest.mappers;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.infrastructure.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductIdRestMapper extends IdentifierMapper<ProductId, String> {

    public static final ProductIdRestMapper INSTANCE = Mappers.getMapper(ProductIdRestMapper.class);
}
