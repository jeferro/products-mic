package com.jeferro.products.products.products.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.products.products.infrastructure.adapters.mongo.dtos.ProductMongoDTO;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.shared.locale.infrastructure.adapters.mongo.mappers.LocalizedDataMongoMapper;
import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	ProductCodeMongoMapper.class,
	LocalizedDataMongoMapper.class
})
public abstract class ProductMongoMapper extends BidirectionalMapper<Product, ProductMongoDTO> {

    public static final ProductMongoMapper INSTANCE = Mappers.getMapper(ProductMongoMapper.class);
}
