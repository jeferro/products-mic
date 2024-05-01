package com.jeferro.products.products.infrastructure.integrations.kafka;

import com.jeferro.products.products.domain.events.ProductEvent;
import com.jeferro.products.products.infrastructure.ProductsProperties;
import com.jeferro.products.products.infrastructure.integrations.kafka.mappers.ProductEventKafkaMapper;
import com.jeferro.products.products.infrastructure.integrations.kafka.mappers.ProductIdKafkaMapper;
import com.jeferro.products.shared.domain.events.EventBusPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventKafkaPublisher implements EventBusPublisher<ProductEvent> {

	private static final Logger logger = LoggerFactory.getLogger(ProductEventKafkaPublisher.class);

	private final ProductIdKafkaMapper productIdKafkaMapper = ProductIdKafkaMapper.INSTANCE;

	private final ProductEventKafkaMapper productEventKafkaMapper = ProductEventKafkaMapper.INSTANCE;

	private final KafkaTemplate<String, Object> kafkaTemplate;

	private final ProductsProperties productsProperties;

	public ProductEventKafkaPublisher(KafkaTemplate<String, Object> kafkaTemplate, ProductsProperties productsProperties) {
		this.kafkaTemplate = kafkaTemplate;
		this.productsProperties = productsProperties;
	}

	@Override
	public void publish(ProductEvent event) {
		var key = productIdKafkaMapper.toDTO(event.getProductId());
		var data = productEventKafkaMapper.toDTO(event);

		kafkaTemplate.send(productsProperties.getTopic(), key, data)
			.exceptionally(cause -> {
				logger.error("Error sending event {}", event, cause);
				return null;
			});
	}
}