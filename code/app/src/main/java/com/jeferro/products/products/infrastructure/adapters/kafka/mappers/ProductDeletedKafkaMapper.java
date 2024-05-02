package com.jeferro.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.components.kafka.KafkaProfile;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import com.jeferro.products.shared.infrastructure.adapters.shared.mappers.ToDTOMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(KafkaProfile.NAME)
public class ProductDeletedKafkaMapper extends ToDTOMapper<ProductDeleted, ProductDeletedAvroDTO> {

	public static final ProductDeletedKafkaMapper INSTANCE = new ProductDeletedKafkaMapper();

	private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

	public ProductDeletedAvroDTO toDTO(ProductDeleted productDeleted) {
		return new ProductDeletedAvroDTO(
			productIdMongoMapper.toDTO(productDeleted.getProductId()),
			productDeleted.getOccurredOn(),
			productDeleted.getOccurredBy());
	}
}
