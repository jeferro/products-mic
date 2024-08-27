package com.jeferro.shared.infrastructure.adapters.kafka.interceptors;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class KafkaConsumerInterceptor implements ConsumerInterceptor<String, String> {

  @Override
  public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> consumerRecords) {
	var authentication = createAuthenticationToken();

	SecurityContextHolder.getContext().setAuthentication(authentication);

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

  @Override
  public void configure(Map<String, ?> map) {
	// Do nothing
  }

  private UsernamePasswordAuthenticationToken createAuthenticationToken() {
	return UsernamePasswordAuthenticationToken.authenticated(
		"system",
		null,
		null);
  }
}
