package com.jeferro.products.components.kafka.products.configurations;

import com.jeferro.products.components.kafka.KafkaProfile;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;


@ConfigurationProperties("components.kafka.products")
@Profile(KafkaProfile.NAME)
public class ProductsKafkaProperties {

	private final String topic;

	public ProductsKafkaProperties(String topic) {
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}
}
