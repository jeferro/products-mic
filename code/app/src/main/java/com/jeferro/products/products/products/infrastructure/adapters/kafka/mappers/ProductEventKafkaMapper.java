package com.jeferro.products.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.generated.kafka.v1.dtos.ProductCreatedAvroDTO;
import com.jeferro.products.generated.kafka.v1.dtos.ProductDeletedAvroDTO;
import com.jeferro.products.generated.kafka.v1.dtos.ProductUpdatedAvroDTO;
import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.products.domain.events.ProductEvent;
import com.jeferro.products.products.products.domain.events.ProductUpdated;
import com.jeferro.shared.infrastructure.adapters.kafka.mappers.EventIdKafkaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	EventIdKafkaMapper.class,
	ProductCodeKafkaMapper.class
})
public abstract class ProductEventKafkaMapper {

  public static final ProductEventKafkaMapper INSTANCE = Mappers.getMapper(ProductEventKafkaMapper.class);

  public Object toDTO(ProductEvent event) {
	return switch (event) {
	  case ProductCreated productCreated -> toDTO(productCreated);
	  case ProductUpdated productUpdated -> toDTO(productUpdated);
	  case ProductDeleted productDeleted -> toDTO(productDeleted);

	  default -> throw new IllegalStateException("Unexpected value: " + event);
	};
  }

  protected abstract ProductCreatedAvroDTO toDTO(ProductCreated entity);

  protected abstract ProductUpdatedAvroDTO toDTO(ProductUpdated entity);

  protected abstract ProductDeletedAvroDTO toDTO(ProductDeleted entity);
}
