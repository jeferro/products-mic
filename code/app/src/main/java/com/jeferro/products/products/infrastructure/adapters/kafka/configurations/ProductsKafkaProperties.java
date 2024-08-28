package com.jeferro.products.products.infrastructure.adapters.kafka.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("application.products")
public record ProductsKafkaProperties(
		String topic
) {

}
