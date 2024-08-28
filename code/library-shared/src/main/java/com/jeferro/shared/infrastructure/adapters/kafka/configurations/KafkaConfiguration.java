package com.jeferro.shared.infrastructure.adapters.kafka.configurations;

import static com.jeferro.shared.infrastructure.adapters.kafka.interceptors.KafkaConsumerInterceptor.CONFIG_SECURITY_MANAGER;
import static org.apache.kafka.clients.consumer.ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG;

import java.util.Map;

import com.jeferro.shared.infrastructure.adapters.kafka.interceptors.KafkaConsumerInterceptor;
import com.jeferro.shared.infrastructure.adapters.security.services.SecurityManager;
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
  public ConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties properties,
	  ObjectProvider<SslBundles> sslBundles,
	  SecurityManager securityManager) {
	Map<String, Object> consumerProperties = properties.buildConsumerProperties(sslBundles.getIfAvailable());

	consumerProperties.put(INTERCEPTOR_CLASSES_CONFIG, KafkaConsumerInterceptor.class.getName());
	consumerProperties.put(CONFIG_SECURITY_MANAGER, securityManager);

	return new DefaultKafkaConsumerFactory<>(consumerProperties);
  }
}
