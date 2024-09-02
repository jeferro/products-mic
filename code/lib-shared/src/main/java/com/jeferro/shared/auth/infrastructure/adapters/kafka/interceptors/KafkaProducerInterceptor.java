package com.jeferro.shared.auth.infrastructure.adapters.kafka.interceptors;

import static com.jeferro.shared.auth.infrastructure.adapters.kafka.KafkaHeaders.HEADER_OCCURRED_BY;
import static com.jeferro.shared.auth.infrastructure.adapters.kafka.KafkaHeaders.HEADER_OCCURRED_ON;

import java.time.Instant;
import java.util.Map;

import com.jeferro.shared.auth.infrastructure.ContextManager;
import com.jeferro.shared.auth.infrastructure.adapters.kafka.KafkaHeaders;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProducerInterceptor implements ProducerInterceptor<String, String> {




  @Override
  public void configure(Map<String, ?> configs) {
	// Do nothing
  }

  @Override
  public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
	var auth = ContextManager.getAuth();
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
