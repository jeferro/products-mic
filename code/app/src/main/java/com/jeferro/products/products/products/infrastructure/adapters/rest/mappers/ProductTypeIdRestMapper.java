package com.jeferro.products.products.products.infrastructure.adapters.rest.mappers;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductTypeId;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductTypeIdRestMapper extends IdentifierMapper<ProductTypeId, String> {

    public static final ProductTypeIdRestMapper INSTANCE = Mappers.getMapper(ProductTypeIdRestMapper.class);
}
