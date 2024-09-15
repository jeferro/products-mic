package com.jeferro.products.products.products.infrastructure.mongo.mappers;

import com.jeferro.products.products.products.infrastructure.mongo.dtos.ProductMongoDTO;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.shared.locale.infrastructure.adapters.mongo.mappers.LocalizedFieldMongoMapper;
import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	ProductCodeMongoMapper.class,
	ProductTypeIdMongoMapper.class,
	LocalizedFieldMongoMapper.class
})
public abstract class ProductMongoMapper extends BidirectionalMapper<Product, ProductMongoDTO> {

    public static final ProductMongoMapper INSTANCE = Mappers.getMapper(ProductMongoMapper.class);
}
