package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.products.dtos.v1.ProductUpdatedAvroDTO;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.shared.infrastructure.adapters.kafka.mappers.EventIdKafkaMapper;
import com.jeferro.shared.infrastructure.mappers.ToDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	EventIdKafkaMapper.class,
	ProductIdKafkaMapper.class
})
public abstract class ProductUpdatedKafkaMapper extends ToDTOMapper<ProductUpdated, ProductUpdatedAvroDTO> {

	public static final ProductUpdatedKafkaMapper INSTANCE = Mappers.getMapper(ProductUpdatedKafkaMapper.class);

}
