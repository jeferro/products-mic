package com.jeferro.products.components.mongodb.shared.services.tenant;

import com.jeferro.products.components.mongodb.shared.configuration.MultiTenantsMongoProperties;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.stereotype.Component;

@Component
public class MultiTenantMongoConnectionManager extends SimpleMongoClientDatabaseFactory {

  private final MultiTenantsMongoProperties multiTenantsMongoProperties;

  public MultiTenantMongoConnectionManager(String connectionString,
	  MultiTenantsMongoProperties multiTenantsMongoProperties) {
	this(new ConnectionString(connectionString), multiTenantsMongoProperties);
  }

  public MultiTenantMongoConnectionManager(ConnectionString connectionString,
	  MultiTenantsMongoProperties multiTenantsMongoProperties) {
	this(MongoClients.create(connectionString), connectionString.getDatabase(), multiTenantsMongoProperties);
  }

  public MultiTenantMongoConnectionManager(MongoClient mongoClient,
	  String databaseName,
	  MultiTenantsMongoProperties multiTenantsMongoProperties) {
	super(mongoClient, databaseName);

	this.multiTenantsMongoProperties = multiTenantsMongoProperties;
  }
}
