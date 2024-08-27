package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.products.dtos.v1.ProductCreatedAvroDTO;
import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.shared.infrastructure.adapters.kafka.mappers.EventIdKafkaMapper;
import com.jeferro.shared.infrastructure.mappers.ToDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	EventIdKafkaMapper.class,
	ProductIdKafkaMapper.class
})
public abstract class ProductCreatedKafkaMapper extends ToDTOMapper<ProductCreated, ProductCreatedAvroDTO> {

	public static final ProductCreatedKafkaMapper INSTANCE = Mappers.getMapper(ProductCreatedKafkaMapper.class);
}
