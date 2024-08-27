package com.jeferro.products.product_reviews.infrastructure.adapters.products_kafka;

import com.jeferro.products.components.kafka.products.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductUpdatedAvroDTO;
import com.jeferro.products.product_reviews.application.params.DeleteAllProductReviewsOfProductParams;
import com.jeferro.products.products.application.params.GetProductParams;
import com.jeferro.products.products.infrastructure.adapters.kafka.mappers.ProductIdKafkaMapper;
import com.jeferro.shared.application.HandlerBus;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
	topics = "${application.products.topic}",
	groupId = "${application.product-reviews.consumer-group-id}"
)
public class ProductReviewsProductsKafkaListener {

  private final ProductIdKafkaMapper productIdKafkaMapper = ProductIdKafkaMapper.INSTANCE;

  private final HandlerBus handlerBus;

  public ProductReviewsProductsKafkaListener(HandlerBus handlerBus) {
	this.handlerBus = handlerBus;
  }

  @KafkaHandler
  protected void consume(ProductDeletedAvroDTO productDeletedAvroDTO) {
	var params = new DeleteAllProductReviewsOfProductParams(
		productIdKafkaMapper.toDomain(productDeletedAvroDTO.getProductId().toString())
	);

	handlerBus.execute(params);
  }

  @KafkaHandler(isDefault = true)
  protected void consume(Object eventAvroDTO) {
	// Do nothing
  }
}
