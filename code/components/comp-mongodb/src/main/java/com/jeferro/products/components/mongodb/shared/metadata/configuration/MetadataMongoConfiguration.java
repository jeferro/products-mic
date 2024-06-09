package com.jeferro.products.components.mongodb.shared.metadata.configuration;

import com.jeferro.products.components.mongodb.shared.metadata.services.CustomMongoTemplate;
import com.jeferro.products.components.mongodb.shared.metadata.services.MongoAuditorAware;
import com.jeferro.products.components.mongodb.shared.metadata.services.MongoFieldManager;
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
