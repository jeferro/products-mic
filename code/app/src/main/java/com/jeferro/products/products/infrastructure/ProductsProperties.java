package com.jeferro.products.products.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("infrastructure.products")
public class ProductsProperties {

    private final String topic;

    public ProductsProperties(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
