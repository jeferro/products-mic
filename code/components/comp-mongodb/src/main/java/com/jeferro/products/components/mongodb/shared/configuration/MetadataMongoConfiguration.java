package com.jeferro.products.components.mongodb.shared.configuration;

import com.jeferro.products.components.mongodb.shared.services.CustomMongoTemplate;
import com.jeferro.products.components.mongodb.shared.services.auditor.MongoAuditorAware;
import com.jeferro.products.components.mongodb.shared.services.auditor.MongoFieldManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MetadataMongoConfiguration {

	@Bean
	@Primary
	public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory,
		MongoAuditorAware mongoAuditorAware,
		MongoFieldManager mongoFieldManager) {
		return new CustomMongoTemplate(mongoDatabaseFactory, mongoAuditorAware, mongoFieldManager);
	}
}
