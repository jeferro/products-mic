package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.KafkaProfile;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductCreatedAvroDTO;
import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.ToDTOMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(KafkaProfile.NAME)
public class ProductCreatedKafkaMapper extends ToDTOMapper<ProductCreated, ProductCreatedAvroDTO> {

	public static final ProductCreatedKafkaMapper INSTANCE = new ProductCreatedKafkaMapper();

	private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

	public ProductCreatedAvroDTO toDTO(ProductCreated productCreated) {
		return new ProductCreatedAvroDTO(
			productIdMongoMapper.toDTO(productCreated.getProductId()),
			productCreated.getOccurredOn(),
			productCreated.getOccurredBy());
	}
}
