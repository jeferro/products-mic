package com.jeferro.products.products.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductIdMongoMapper extends IdentifierMapper<ProductId, String> {

    public static final ProductIdMongoMapper INSTANCE = Mappers.getMapper(ProductIdMongoMapper.class);
}
