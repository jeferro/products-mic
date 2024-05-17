package com.jeferro.products.components.kafka.products.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("components.kafka.products")
public record ProductsKafkaProperties(
		String topic
) {

}
