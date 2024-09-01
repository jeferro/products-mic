package com.jeferro.products.products.products.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.generated.kafka.v1.dtos.ProductCreatedAvroDTO;
import com.jeferro.products.generated.kafka.v1.dtos.ProductDeletedAvroDTO;
import com.jeferro.products.generated.kafka.v1.dtos.ProductPublishedAvroDTO;
import com.jeferro.products.generated.kafka.v1.dtos.ProductUnpublishedAvroDTO;
import com.jeferro.products.generated.kafka.v1.dtos.ProductUpdatedAvroDTO;
import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.products.domain.events.ProductEvent;
import com.jeferro.products.products.products.domain.events.ProductPublished;
import com.jeferro.products.products.products.domain.events.ProductUnpublished;
import com.jeferro.products.products.products.domain.events.ProductUpdated;
import com.jeferro.shared.infrastructure.adapters.kafka.mappers.EventIdKafkaMapper;
import com.jeferro.shared.infrastructure.adapters.kafka.mappers.LocalizedDataKafkaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	EventIdKafkaMapper.class,
	ProductCodeKafkaMapper.class,
	LocalizedDataKafkaMapper.class
})
public abstract class ProductEventKafkaMapper {

  public static final ProductEventKafkaMapper INSTANCE = Mappers.getMapper(ProductEventKafkaMapper.class);

  public Object toDTO(ProductEvent event) {
	return switch (event) {
	  case ProductCreated productCreated -> toDTO(productCreated);
	  case ProductUpdated productUpdated -> toDTO(productUpdated);
	  case ProductPublished productPublished -> toDTO(productPublished);
	  case ProductUnpublished productUnpublished -> toDTO(productUnpublished);
	  case ProductDeleted productDeleted -> toDTO(productDeleted);

	  default -> throw new IllegalStateException("Unexpected value: " + event);
	};
  }

  protected abstract ProductCreatedAvroDTO toDTO(ProductCreated entity);

  protected abstract ProductUpdatedAvroDTO toDTO(ProductUpdated entity);

  protected abstract ProductPublishedAvroDTO toDTO(ProductPublished entity);

  protected abstract ProductUnpublishedAvroDTO toDTO(ProductUnpublished entity);

  protected abstract ProductDeletedAvroDTO toDTO(ProductDeleted entity);
}
