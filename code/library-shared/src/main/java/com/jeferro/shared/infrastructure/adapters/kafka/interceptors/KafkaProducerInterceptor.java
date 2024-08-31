package com.jeferro.shared.infrastructure.adapters.kafka.interceptors;

import java.time.Instant;
import java.util.Map;

import com.jeferro.shared.infrastructure.adapters.security.services.SecurityManager;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProducerInterceptor implements ProducerInterceptor<String, String> {

  public static final String CONFIG_SECURITY_MANAGER = "security-manager";

  public static final String HEADER_OCCURRED_BY = "app-occurred-by";

  public static final String HEADER_OCCURRED_ON = "app-occurred-on";

  private SecurityManager securityManager;

  @Override
  public void configure(Map<String, ?> configs) {
	this.securityManager = (SecurityManager) configs.get(CONFIG_SECURITY_MANAGER);
  }

  @Override
  public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
	var auth = securityManager.getAuth();
	var now = Instant.now();

	producerRecord.headers()
		.add(HEADER_OCCURRED_BY, auth.who().getBytes())
		.add(HEADER_OCCURRED_ON, now.toString().getBytes());

	return producerRecord;
  }

  @Override
  public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
	// Do nothing
  }

  @Override
  public void close() {
	// Do nothing
  }
}
