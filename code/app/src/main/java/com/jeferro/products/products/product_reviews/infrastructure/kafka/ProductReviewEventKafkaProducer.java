package com.jeferro.products.products.product_reviews.infrastructure.kafka;

import com.jeferro.products.products.product_reviews.domain.events.ProductReviewEvent;
import com.jeferro.products.products.product_reviews.infrastructure.kafka.mappers.ProductReviewKafkaMapper;
import com.jeferro.shared.ddd.domain.events.EventBusProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductReviewEventKafkaProducer implements EventBusProducer<ProductReviewEvent> {

  private final ProductReviewKafkaMapper productReviewKafkaMapper = ProductReviewKafkaMapper.INSTANCE;

  @Value("${application.product-reviews.topic}")
  private String topic;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void send(ProductReviewEvent event) {
	String key = event.getProductReviewId().toString();
	var data = productReviewKafkaMapper.toDTO(event);

	kafkaTemplate.send(topic, key, data);
  }

}