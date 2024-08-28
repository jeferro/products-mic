package com.jeferro.products.product_details.infrastructure.adapters.products_kafka;

import com.jeferro.products.components.kafka.products.dtos.v1.ProductCreatedAvroDTO;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductUpdatedAvroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
	topics = "${application.products.topic}",
	groupId = "${application.product-details.consumer-group-id}"
)
public class ProductDetailsProductsKafkaListener {

  private static final Logger logger = LoggerFactory.getLogger(ProductDetailsProductsKafkaListener.class);

  @KafkaHandler
  protected void consume(ProductCreatedAvroDTO productCreatedAvroDTO) {
	logger.info("Consumed product created from topic: {}", productCreatedAvroDTO);
  }

  @KafkaHandler
  protected void consume(ProductUpdatedAvroDTO productUpdatedAvroDTO) {
	logger.info("Consumed product updated from topic: {}", productUpdatedAvroDTO);
  }

  @KafkaHandler
  protected void consume(ProductDeletedAvroDTO productDeletedAvroDTO) {
	logger.info("Consumed product deleted from topic: {}", productDeletedAvroDTO);
  }

  @KafkaHandler(isDefault = true)
  protected void consume(Object eventAvroDTO) {
	// Do nothing
  }
}
