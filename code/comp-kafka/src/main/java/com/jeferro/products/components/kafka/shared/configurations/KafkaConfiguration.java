package com.jeferro.products.components.kafka.shared.configurations;

import com.jeferro.products.components.kafka.KafkaProfile;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableConfigurationProperties
@Profile(KafkaProfile.NAME)
public class KafkaConfiguration {

}
