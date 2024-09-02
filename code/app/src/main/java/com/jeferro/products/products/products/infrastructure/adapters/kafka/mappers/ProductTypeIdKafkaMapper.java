package com.jeferro.products.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;
import com.jeferro.shared.mappers.IdentifierMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductTypeIdKafkaMapper extends IdentifierMapper<ProductTypeId, String> {

	public static final ProductTypeIdKafkaMapper INSTANCE = Mappers.getMapper(ProductTypeIdKafkaMapper.class);

	public ProductTypeId toDomain(CharSequence value) {
	  	return toDomain(value.toString());
	}

}
