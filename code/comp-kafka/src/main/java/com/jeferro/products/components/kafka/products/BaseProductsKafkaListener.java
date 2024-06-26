package com.jeferro.products.components.kafka.products;

import com.jeferro.products.components.kafka.products.dtos.v1.ProductCreatedAvroDTO;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductUpdatedAvroDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public abstract class BaseProductsKafkaListener {

	@KafkaListener(topics = "${components.kafka.products.topic}",
		groupId = "#{__listener.groupId}")
	final public void consume(ConsumerRecord<String, Object> record) {
		var value = record.value();

		switch (value) {
			case ProductCreatedAvroDTO productCreatedAvroDTO -> consume(productCreatedAvroDTO);
			case ProductUpdatedAvroDTO productUpdatedAvroDTO -> consume(productUpdatedAvroDTO);
			case ProductDeletedAvroDTO productDeletedAvroDTO -> consume(productDeletedAvroDTO);
			default -> throw new IllegalStateException("Unexpected kafka event: " + record);
		}
	}

	protected abstract String getGroupId();

	protected void consume(ProductCreatedAvroDTO productCreatedAvroDTO) {
	  // Do nothing
	}

	protected void consume(ProductUpdatedAvroDTO productUpdatedAvroDTO) {
	  // Do nothing
	}

	protected void consume(ProductDeletedAvroDTO productDeletedAvroDTO) {
	  // Do nothing
	}
}
