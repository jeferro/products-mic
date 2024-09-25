package com.jeferro.products.products.products.infrastructure.kafka.mappers;

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
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.mappers.EventMapper;
import com.jeferro.shared.mappers.MapstructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public abstract class ProductKafkaMapper extends EventMapper<ProductEvent> {

  public static final ProductKafkaMapper INSTANCE = Mappers.getMapper(ProductKafkaMapper.class);

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

  public abstract ProductCode toDomain(String value);
}
