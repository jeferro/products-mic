package com.jeferro.products.products.infrastructure.integrations.kafka.mappers;

import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.domain.events.ProductEvent;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.infrastructure.integrations.kafka.dtos.v1.ProductCreatedAvroDTO;
import com.jeferro.products.products.infrastructure.integrations.kafka.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.products.infrastructure.integrations.kafka.dtos.v1.ProductUpdatedAvroDTO;
import com.jeferro.products.products.infrastructure.integrations.mongo.mappers.ProductIdMongoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductEventKafkaMapper {

	public static final ProductEventKafkaMapper INSTANCE = new ProductEventKafkaMapper();

	private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

	public Object toDTO(ProductEvent productEvent) {
		return switch (productEvent) {
			case ProductCreated productCreated -> toDTO(productCreated);
			case ProductUpdated productUpdated -> toDTO(productUpdated);
			case ProductDeleted productDeleted -> toDTO(productDeleted);
			case ProductEvent ignored -> throw new IllegalArgumentException("Unexpected domain event:  " + productEvent);
		};
	}

	public ProductCreatedAvroDTO toDTO(ProductCreated productCreated) {
		ProductCreatedAvroDTO.Builder builder = ProductCreatedAvroDTO.newBuilder();
		builder.setProductId(productIdMongoMapper.toDTO(productCreated.getProductId()));
		builder.setOccurredOn(productCreated.getOccurredOn());
		builder.setOccurredBy(productCreated.getOccurredBy());
		return builder.build();
	}

	protected ProductUpdatedAvroDTO toDTO(ProductUpdated productUpdated) {
		return new ProductUpdatedAvroDTO(
			productIdMongoMapper.toDTO(productUpdated.getProductId()),
			productUpdated.getOccurredOn(),
			productUpdated.getOccurredBy()
		);
	}

	protected ProductDeletedAvroDTO toDTO(ProductDeleted productDeleted) {
		return new ProductDeletedAvroDTO(
			productIdMongoMapper.toDTO(productDeleted.getProductId()),
			productDeleted.getOccurredOn(),
			productDeleted.getOccurredBy()
		);
	}
}
