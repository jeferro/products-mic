package com.jeferro.products.products.products.infrastructure.adapters.rest.mappers;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.infrastructure.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductCodeRestMapper extends IdentifierMapper<ProductCode, String> {

    public static final ProductCodeRestMapper INSTANCE = Mappers.getMapper(ProductCodeRestMapper.class);
}
