package com.jeferro.shared.infrastructure.adapters.kafka.configurations;

import java.util.Map;

import com.jeferro.shared.infrastructure.adapters.kafka.interceptors.KafkaConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConfiguration {

  @Bean
  public ConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties properties, ObjectProvider<SslBundles> sslBundles) {
	Map<String, Object> consumerProperties = properties.buildConsumerProperties(sslBundles.getIfAvailable());

	consumerProperties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, KafkaConsumerInterceptor.class.getName());

	return new DefaultKafkaConsumerFactory<>(consumerProperties);
  }
}
