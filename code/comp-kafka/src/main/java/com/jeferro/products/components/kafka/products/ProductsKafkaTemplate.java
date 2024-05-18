package com.jeferro.products.components.kafka.products;

import com.jeferro.products.components.kafka.products.configurations.ProductsKafkaProperties;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductCreatedAvroDTO;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductUpdatedAvroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductsKafkaTemplate {

	private static final Logger logger = LoggerFactory.getLogger(ProductsKafkaTemplate.class);

	private final KafkaTemplate<String, Object> kafkaTemplate;

	private final ProductsKafkaProperties productsKafkaProperties;

	public ProductsKafkaTemplate(KafkaTemplate<String, Object> kafkaTemplate,
		ProductsKafkaProperties productsKafkaProperties) {
		this.kafkaTemplate = kafkaTemplate;
		this.productsKafkaProperties = productsKafkaProperties;
	}

	public void send(ProductCreatedAvroDTO event) {
		String key = event.getProductId().toString();

		kafkaTemplate.send(productsKafkaProperties.topic(), key, event)
			.exceptionally(cause -> {
				logger.error("Error sending event {}", event, cause);
				return null;
			});
	}

	public void send(ProductUpdatedAvroDTO event) {
		String key = event.getProductId().toString();

		kafkaTemplate.send(productsKafkaProperties.topic(), key, event)
			.exceptionally(cause -> {
				logger.error("Error sending event {}", event, cause);
				return null;
			});
	}

	public void send(ProductDeletedAvroDTO event) {
		String key = event.getProductId().toString();

		kafkaTemplate.send(productsKafkaProperties.topic(), key, event)
			.exceptionally(cause -> {
				logger.error("Error sending event {}", event, cause);
				return null;
			});
	}
}
