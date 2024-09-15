package com.jeferro.products.products.products.infrastructure.kafka.mappers;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductCodeKafkaMapper extends IdentifierMapper<ProductCode, String> {

	public static final ProductCodeKafkaMapper INSTANCE = Mappers.getMapper(ProductCodeKafkaMapper.class);

	public ProductCode toDomain(CharSequence value) {
	  	return toDomain(value.toString());
	}

}
