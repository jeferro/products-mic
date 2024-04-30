package com.jeferro.products.shared.infrastructure.integrations.mongo.services;

import java.time.Instant;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class MetadataMongoTemplate {

  private static final String ID = "id";

  private final MongoUsernameResolver mongoUsernameResolver;

  private final MongoFieldManager mongoFieldManager;

  private final MongoTemplate mongoTemplate;

  public MetadataMongoTemplate(MongoUsernameResolver mongoUsernameResolver,
                               MongoFieldManager mongoFieldManager,
                               MongoTemplate mongoTemplate) {
    this.mongoUsernameResolver = mongoUsernameResolver;
    this.mongoFieldManager = mongoFieldManager;
    this.mongoTemplate = mongoTemplate;
  }

  public void save(final Object document) {
    final Update update = new Update();
    updateAllFields(update, document);
    updateMetadataFields(update);

    Criteria criteria = Criteria.where(ID)
            .is(mongoFieldManager.getFieldIdValue(document));
    final Query query = new Query(criteria);

    mongoTemplate.upsert(query, update, document.getClass());
  }

  private void updateAllFields(Update update, Object document) {
    final Map<String, Object> fieldsToUpdate = mongoFieldManager.calculateFieldToUpdate(document);

    if (fieldsToUpdate.isEmpty()) {
      throw new RuntimeException("No updatable fields found in the document of type " + document.getClass().getSimpleName());
    }

    fieldsToUpdate.forEach(update::set);
  }

  private void updateMetadataFields(Update update) {
    Instant now = Instant.now();
    String username = mongoUsernameResolver.resolve();

    update.setOnInsert("metadata.createdVia", "reef");
    update.setOnInsert("metadata.createdBy", username);
    update.setOnInsert("metadata.createdAt", now);

    update.set("metadata.updatedVia", "reef");
    update.set("metadata.updatedAt", now);
    update.set("metadata.updatedBy", username);
  }


}
