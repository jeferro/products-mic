package com.jeferro.products.products.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.mongodb.products.dtos.ProductMongoDTO;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.shared.infrastructure.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	ProductIdMongoMapper.class
})
public abstract class ProductMongoMapper extends BidirectionalMapper<Product, ProductMongoDTO> {

    public static final ProductMongoMapper INSTANCE = Mappers.getMapper(ProductMongoMapper.class);
}
