package com.jeferro.products.products.products.infrastructure.kafka;

import com.jeferro.products.products.products.domain.events.ProductEvent;
import com.jeferro.products.products.products.infrastructure.ProductsProperties;
import com.jeferro.products.products.products.infrastructure.kafka.mappers.ProductKafkaMapper;
import com.jeferro.shared.ddd.domain.events.EventBusPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventKafkaPublisher implements EventBusPublisher<ProductEvent> {

  private final ProductKafkaMapper productKafkaMapper = ProductKafkaMapper.INSTANCE;

  private final ProductsProperties productsProperties;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public ProductEventKafkaPublisher(ProductsProperties productsProperties,
	  KafkaTemplate<String, Object> kafkaTemplate) {
	this.productsProperties = productsProperties;
	this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void publish(ProductEvent event) {
	String key = event.getCode().toString();
	var data = productKafkaMapper.toDTO(event);

	kafkaTemplate.send(productsProperties.topic(), key, data);
  }

}