package com.jeferro.products.products.products.infrastructure.mongo.mappers;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.infrastructure.mongo.dtos.ProductMongoDTO;
import com.jeferro.shared.mappers.MapstructConfig;
import com.jeferro.shared.mappers.SecondaryAggregateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public abstract class ProductMongoMapper extends SecondaryAggregateMapper<Product, ProductCode, ProductMongoDTO> {

    public static final ProductMongoMapper INSTANCE = Mappers.getMapper(ProductMongoMapper.class);
}
