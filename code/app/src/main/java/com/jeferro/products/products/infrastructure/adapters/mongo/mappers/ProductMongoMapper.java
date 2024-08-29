package com.jeferro.products.products.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.products.infrastructure.adapters.mongo.dtos.ProductMongoDTO;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.shared.infrastructure.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	ProductCodeMongoMapper.class
})
public abstract class ProductMongoMapper extends BidirectionalMapper<Product, ProductMongoDTO> {

    public static final ProductMongoMapper INSTANCE = Mappers.getMapper(ProductMongoMapper.class);
}
