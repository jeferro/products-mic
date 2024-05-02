package com.jeferro.products.shared.infrastructure.adapters.mongo;

import com.jeferro.products.components.mongodb.MongoProfile;
import com.jeferro.products.components.mongodb.shared.services.MetadataMongoTemplate;
import com.jeferro.products.components.mongodb.shared.services.MongoFieldManager;
import com.jeferro.products.components.mongodb.shared.services.MongoUsernameResolver;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataMongoTest
@Testcontainers
@ActiveProfiles(MongoProfile.NAME)
@Import({
	MetadataMongoTemplate.class,
	MongoUsernameResolver.class,
	MongoFieldManager.class
})
public abstract class MongoRepositoryIT {

	private static final String MONGODB_VERSION = "mongo:7.0";

	@Container
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(MONGODB_VERSION);
}
