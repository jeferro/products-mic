package com.jeferro.products.products.infrastructure.adapters.mongo.mappers;

import com.jeferro.products.components.mongodb.MongoProfile;
import com.jeferro.products.components.mongodb.products.dtos.ProductMongoDTO;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper(uses = {
	ProductIdMongoMapper.class
})
@Profile(MongoProfile.NAME)
public abstract class ProductMongoMapper extends BidirectionalMapper<Product, ProductMongoDTO> {

    public static final ProductMongoMapper INSTANCE = Mappers.getMapper(ProductMongoMapper.class);
}
