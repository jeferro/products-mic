package com.jeferro.products.product_details.infrastructure.integrations.products_kafka;

import com.jeferro.products.products.infrastructure.integrations.kafka.dtos.v1.ProductCreatedAvroDTO;
import com.jeferro.products.products.infrastructure.integrations.kafka.dtos.v1.ProductDeletedAvroDTO;
import com.jeferro.products.products.infrastructure.integrations.kafka.dtos.v1.ProductUpdatedAvroDTO;
import com.jeferro.products.shared.domain.exceptions.ApplicationException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductsKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ProductsKafkaConsumer.class);

    @KafkaListener(topics = "${infrastructure.products.topic}", groupId = "product-details")
    public void consume(ConsumerRecord<String, Object> record) {
        try {
            var value = record.value();

            switch (value) {
                case ProductCreatedAvroDTO ignored -> logger.info("Consumed product created from topic: {}", value);
                case ProductUpdatedAvroDTO ignored -> logger.info("Consumed product updated from topic: {}", value);
                case ProductDeletedAvroDTO ignored -> logger.info("Consumed product deleted from topic: {}", value);
                default -> throw new IllegalStateException("Unexpected kafka event: " + record);
            }
        } catch (ApplicationException cause) {
            // Do nothing
        } catch (Exception cause) {
            logger.error(cause.getMessage(), cause);
        }
    }
}
