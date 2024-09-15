package com.jeferro.products.products.product_reviews.infrastructure.kafka;

import com.jeferro.products.products.product_reviews.domain.events.ProductReviewEvent;
import com.jeferro.products.products.product_reviews.infrastructure.ProductReviewsProperties;
import com.jeferro.products.products.product_reviews.infrastructure.kafka.mappers.ProductReviewEventKafkaMapper;
import com.jeferro.shared.ddd.domain.events.EventBusPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewEventKafkaPublisher implements EventBusPublisher<ProductReviewEvent> {

  private final ProductReviewEventKafkaMapper productReviewEventKafkaMapper = ProductReviewEventKafkaMapper.INSTANCE;

  private final ProductReviewsProperties productReviewsProperties;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public ProductReviewEventKafkaPublisher(ProductReviewsProperties productReviewsProperties,
	  KafkaTemplate<String, Object> kafkaTemplate) {
	this.productReviewsProperties = productReviewsProperties;
	this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void publish(ProductReviewEvent event) {
	String key = event.getProductReviewId().toString();
	var data = productReviewEventKafkaMapper.toDTO(event);

	kafkaTemplate.send(productReviewsProperties.topic(), key, data);
  }

}