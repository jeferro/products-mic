package com.jeferro.products.shared.infrastructure.integrations.mongo.dtos;

import java.time.Instant;

public record MetadataMongoDTO(
        Instant createdAt,
        String createdBy,
        Instant updatedAt,
        String updatedBy
) {
}
