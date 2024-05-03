package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.KafkaProfile;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductCreatedAvroDTO;
import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.ToDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper(uses = {
	ProductIdMongoMapper.class
})
@Profile(KafkaProfile.NAME)
public abstract class ProductEventKafkaMapper extends ToDTOMapper<ProductCreated, ProductCreatedAvroDTO> {

	public static final ProductEventKafkaMapper INSTANCE = Mappers.getMapper(ProductEventKafkaMapper.class);
}
