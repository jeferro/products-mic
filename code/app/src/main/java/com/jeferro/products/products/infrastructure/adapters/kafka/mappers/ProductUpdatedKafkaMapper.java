package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.KafkaProfile;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductUpdatedAvroDTO;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.ToDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper(uses = {
	ProductIdMongoMapper.class
})
@Profile(KafkaProfile.NAME)
public abstract class ProductUpdatedKafkaMapper extends ToDTOMapper<ProductUpdated, ProductUpdatedAvroDTO> {

	public static final ProductUpdatedKafkaMapper INSTANCE = Mappers.getMapper(ProductUpdatedKafkaMapper.class);

}
