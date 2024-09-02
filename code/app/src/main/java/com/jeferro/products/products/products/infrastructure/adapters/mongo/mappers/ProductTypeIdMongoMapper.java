package com.jeferro.products.products.products.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.products.products.domain.models.ProductTypeId;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductTypeIdMongoMapper extends IdentifierMapper<ProductTypeId, String> {

    public static final ProductTypeIdMongoMapper INSTANCE = Mappers.getMapper(ProductTypeIdMongoMapper.class);
}
