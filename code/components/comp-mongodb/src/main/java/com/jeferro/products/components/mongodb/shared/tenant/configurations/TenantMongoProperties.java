package com.jeferro.products.components.mongodb.shared.tenant.configurations;

import com.mongodb.MongoClientSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.mongo.multi-tenant")
public record TenantMongoProperties(
    String tenant,
    MongoClientSettings properties
) {
}
