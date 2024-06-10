package com.jeferro.products.components.mongodb.shared.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.mongo.multi-tenant")
public record MultiTenantsMongoProperties(
    boolean enabled,
    List<TenantMongoProperties> tenants
) {


}
