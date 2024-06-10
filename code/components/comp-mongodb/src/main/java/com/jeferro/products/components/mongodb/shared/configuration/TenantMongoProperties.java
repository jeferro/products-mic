package com.jeferro.products.components.mongodb.shared.configuration;

import com.mongodb.MongoClientSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;

public record TenantMongoProperties(
    String tenant,
    MongoClientSettings properties
) {
}
