package com.jeferro.products.products.infrastructure.integrations.kafka;

import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.infrastructure.integrations.kafka.mappers.ProductCreatedKafkaMapper;
import com.jeferro.products.products.infrastructure.integrations.kafka.mappers.ProductDeletedKafkaMapper;
import com.jeferro.products.products.infrastructure.integrations.kafka.mappers.ProductIdKafkaMapper;
import com.jeferro.products.products.infrastructure.integrations.kafka.mappers.ProductUpdatedKafkaMapper;
import com.jeferro.products.shared.domain.events.EventBusPublisher;
import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.domain.events.ProductEvent;
import com.jeferro.products.products.infrastructure.ProductsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventKafkaPublisher implements EventBusPublisher<ProductEvent> {

    private static final Logger logger = LoggerFactory.getLogger(UserEventKafkaPublisher.class);

    private final ProductIdKafkaMapper productIdKafkaMapper = ProductIdKafkaMapper.INSTANCE;

    private final ProductCreatedKafkaMapper productCreatedKafkaMapper = ProductCreatedKafkaMapper.INSTANCE;

    private final ProductUpdatedKafkaMapper productUpdatedKafkaMapper = ProductUpdatedKafkaMapper.INSTANCE;

    private final ProductDeletedKafkaMapper productDeletedKafkaMapper = ProductDeletedKafkaMapper.INSTANCE;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final ProductsProperties productsProperties;

    public UserEventKafkaPublisher(KafkaTemplate<String, Object> kafkaTemplate, ProductsProperties productsProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.productsProperties = productsProperties;
    }

    @Override
    public void publish(ProductEvent event) {
        var key = productIdKafkaMapper.toDTO(event.getProductId());

        var data = switch (event) {
            case ProductCreated productCreated -> productCreatedKafkaMapper.toDTO(productCreated);
            case ProductUpdated productUpdated -> productUpdatedKafkaMapper.toDTO(productUpdated);
            case ProductDeleted productDeleted -> productDeletedKafkaMapper.toDTO(productDeleted);
            case ProductEvent ignored -> throw new IllegalArgumentException("Unexpected domain event:  " + event);
        };

        kafkaTemplate.send(productsProperties.getTopic(), key, data)
                .exceptionally(cause -> {
                    logger.error("Error sending event {}", event, cause);
                    return null;
                });
    }
}