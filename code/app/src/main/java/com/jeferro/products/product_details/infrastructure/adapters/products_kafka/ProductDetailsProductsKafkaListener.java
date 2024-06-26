package com.jeferro.products.product_details.infrastructure.adapters.products_kafka;

import com.jeferro.products.components.kafka.products.BaseProductsKafkaListener;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductCreatedAvroDTO;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.components.kafka.products.dtos.v1.ProductUpdatedAvroDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailsProductsKafkaListener extends BaseProductsKafkaListener {

	private static final Logger logger = LoggerFactory.getLogger(ProductDetailsProductsKafkaListener.class);

	@Override
	public String getGroupId() {
		return "product-details";
	}

	@Override
	protected void consume(ProductCreatedAvroDTO productCreatedAvroDTO) {
		logger.info("Consumed product created from topic: {}", productCreatedAvroDTO);
	}

	@Override
	protected void consume(ProductUpdatedAvroDTO productUpdatedAvroDTO) {
		logger.info("Consumed product updated from topic: {}", productUpdatedAvroDTO);
	}

	@Override
	protected void consume(ProductDeletedAvroDTO productDeletedAvroDTO) {
		logger.info("Consumed product deleted from topic: {}", productDeletedAvroDTO);
	}
}
