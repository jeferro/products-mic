package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.infrastructure.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductIdKafkaMapper extends IdentifierMapper<ProductId, String> {

	public static final ProductIdKafkaMapper INSTANCE = Mappers.getMapper(ProductIdKafkaMapper.class);

}
