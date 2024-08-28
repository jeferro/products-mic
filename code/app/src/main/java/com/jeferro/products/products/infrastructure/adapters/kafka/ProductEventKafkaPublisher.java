package com.jeferro.products.products.infrastructure.adapters.kafka;

import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.domain.events.ProductEvent;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.infrastructure.adapters.kafka.mappers.ProductCreatedKafkaMapper;
import com.jeferro.products.products.infrastructure.adapters.kafka.mappers.ProductDeletedKafkaMapper;
import com.jeferro.products.products.infrastructure.adapters.kafka.mappers.ProductUpdatedKafkaMapper;
import com.jeferro.shared.domain.events.EventBusPublisher;
import org.springframework.stereotype.Component;

@Component
public class ProductEventKafkaPublisher implements EventBusPublisher<ProductEvent> {

	private final ProductCreatedKafkaMapper productCreatedKafkaMapper = ProductCreatedKafkaMapper.INSTANCE;

	private final ProductUpdatedKafkaMapper productUpdatedKafkaMapper = ProductUpdatedKafkaMapper.INSTANCE;

	private final ProductDeletedKafkaMapper productDeletedKafkaMapper = ProductDeletedKafkaMapper.INSTANCE;

	private final ProductsKafkaTemplate productsKafkaTemplate;

	public ProductEventKafkaPublisher(ProductsKafkaTemplate productsKafkaTemplate) {
		this.productsKafkaTemplate = productsKafkaTemplate;
	}

	@Override
	public void publish(ProductEvent event) {
		switch (event) {
			case ProductCreated productCreated -> sendProductCreated(productCreated);
			case ProductUpdated productUpdated -> sendProductUpdated(productUpdated);
			case ProductDeleted productDeleted -> sendProductDeleted(productDeleted);
			default -> throw new IllegalStateException("Unexpected value: " + event);
		}
	}

	private void sendProductCreated(ProductCreated event) {
		var eventDto = productCreatedKafkaMapper.toDTO(event);

		productsKafkaTemplate.send(eventDto);
	}

	private void sendProductUpdated(ProductUpdated event) {
		var eventDto = productUpdatedKafkaMapper.toDTO(event);

		productsKafkaTemplate.send(eventDto);
	}

	private void sendProductDeleted(ProductDeleted event) {
		var eventDto = productDeletedKafkaMapper.toDTO(event);

		productsKafkaTemplate.send(eventDto);
	}
}