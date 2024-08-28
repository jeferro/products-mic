package com.jeferro.shared.infrastructure.adapters.kafka.interceptors;

import java.util.Map;

import com.jeferro.shared.infrastructure.adapters.security.services.SecurityManager;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class KafkaConsumerInterceptor implements ConsumerInterceptor<String, String> {

  public static final String CONFIG_SECURITY_MANAGER = "security-manager";

  private SecurityManager securityManager;

  @Override
  public void configure(Map<String, ?> configs) {
	this.securityManager = (SecurityManager) configs.get(CONFIG_SECURITY_MANAGER);
  }

  @Override
  public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> consumerRecords) {
	securityManager.signInSystem();

	return consumerRecords;
  }

  @Override
  public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {
	// Do nothing
  }

  @Override
  public void close() {
	// Do nothing
  }

  private UsernamePasswordAuthenticationToken createAuthenticationToken() {
	return UsernamePasswordAuthenticationToken.authenticated(
		"system",
		null,
		null);
  }
}
