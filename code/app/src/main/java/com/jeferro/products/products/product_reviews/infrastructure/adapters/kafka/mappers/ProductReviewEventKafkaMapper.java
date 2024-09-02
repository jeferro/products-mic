package com.jeferro.products.products.product_reviews.infrastructure.adapters.kafka.mappers;

import com.jeferro.products.generated.kafka.v1.dtos.ProductReviewCreatedAvroDTO;
import com.jeferro.products.generated.kafka.v1.dtos.ProductReviewDeletedAvroDTO;
import com.jeferro.products.generated.kafka.v1.dtos.ProductReviewUpdatedAvroDTO;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewCreated;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewEvent;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewUpdated;
import com.jeferro.shared.ddd.infrastructure.adapters.kafka.mappers.EventIdKafkaMapper;
import com.jeferro.shared.locale.infrastructure.adapters.kafka.mappers.LocaleKafkaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
	EventIdKafkaMapper.class,
	LocaleKafkaMapper.class,
	ProductReviewIdKafkaMapper.class
})
public abstract class ProductReviewEventKafkaMapper {

  public static final ProductReviewEventKafkaMapper INSTANCE = Mappers.getMapper(ProductReviewEventKafkaMapper.class);

  public Object toDTO(ProductReviewEvent event) {
	return switch (event) {
	  case ProductReviewCreated productReviewCreated -> toDTO(productReviewCreated);
	  case ProductReviewUpdated productReviewUpdated -> toDTO(productReviewUpdated);
	  case ProductReviewDeleted productReviewDeleted -> toDTO(productReviewDeleted);

	  default -> throw new IllegalStateException("Unexpected value: " + event);
	};
  }

  protected abstract ProductReviewCreatedAvroDTO toDTO(ProductReviewCreated entity);

  protected abstract ProductReviewUpdatedAvroDTO toDTO(ProductReviewUpdated entity);

  protected abstract ProductReviewDeletedAvroDTO toDTO(ProductReviewDeleted entity);
}
