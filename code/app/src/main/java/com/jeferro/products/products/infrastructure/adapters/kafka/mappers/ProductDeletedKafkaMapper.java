package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.products.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.shared.infrastructure.adapters.kafka.mappers.EventIdKafkaMapper;
import com.jeferro.shared.infrastructure.mappers.ToDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	EventIdKafkaMapper.class,
	ProductIdKafkaMapper.class
})
public abstract class ProductDeletedKafkaMapper extends ToDTOMapper<ProductDeleted, ProductDeletedAvroDTO> {

	public static final ProductDeletedKafkaMapper INSTANCE = Mappers.getMapper(ProductDeletedKafkaMapper.class);
}
