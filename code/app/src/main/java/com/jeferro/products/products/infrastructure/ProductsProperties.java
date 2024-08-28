package com.jeferro.products.products.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("application.products")
public record ProductsProperties(
		String topic
) {

}
