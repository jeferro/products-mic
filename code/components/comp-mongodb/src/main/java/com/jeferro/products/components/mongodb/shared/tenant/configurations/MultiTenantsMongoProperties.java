package com.jeferro.products.components.mongodb.shared.tenant.configurations;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.mongo.multi-tenant")
public record MultiTenantsMongoProperties(
    boolean enabled,
    List<TenantMongoProperties> tenants
) {


}
