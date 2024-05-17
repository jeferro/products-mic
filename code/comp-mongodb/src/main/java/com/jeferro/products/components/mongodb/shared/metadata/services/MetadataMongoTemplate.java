package com.jeferro.products.components.mongodb.shared.metadata.services;

import java.time.Instant;
import java.util.Map;

import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class MetadataMongoTemplate extends MongoTemplate {

	private static final String ID = "id";

	private final MongoAuditorAware mongoAuditorAware;

	private final MongoFieldManager mongoFieldManager;

	public MetadataMongoTemplate(MongoDatabaseFactory mongoDatabaseFactory,
		MongoAuditorAware mongoAuditorAware,
		MongoFieldManager mongoFieldManager) {
		super(mongoDatabaseFactory);

		this.mongoAuditorAware = mongoAuditorAware;
		this.mongoFieldManager = mongoFieldManager;
	}

	@Override
	public <T> T save(T objectToSave, String collectionName) {
		final Update update = new Update();
		updateAllFields(update, objectToSave);
		updateMetadataFields(update);

		Criteria criteria = Criteria.where(ID)
			.is(mongoFieldManager.getFieldIdValue(objectToSave));
		final Query query = new Query(criteria);

		upsert(query, update, objectToSave.getClass());

		return objectToSave;
	}

	private void updateAllFields(Update update, Object objectToSave) {
		final Map<String, Object> fieldsToUpdate = mongoFieldManager.calculateFieldToUpdate(objectToSave);

		if (fieldsToUpdate.isEmpty()) {
			throw new RuntimeException("No updatable fields found in the document of type " + objectToSave.getClass().getSimpleName());
		}

		fieldsToUpdate.forEach(update::set);
	}

	private void updateMetadataFields(Update update) {
		Instant now = Instant.now();
		String username = mongoAuditorAware.getCurrentAuditor()
			.orElseThrow();

		update.setOnInsert("metadata.createdBy", username);
		update.setOnInsert("metadata.createdAt", now);

		update.set("metadata.updatedAt", now);
		update.set("metadata.updatedBy", username);
	}

}
