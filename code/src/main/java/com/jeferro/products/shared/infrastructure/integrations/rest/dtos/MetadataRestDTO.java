package com.jeferro.products.shared.infrastructure.integrations.rest.dtos;

import java.time.Instant;

public record MetadataRestDTO(
        Instant createdAt,
        String createdBy,
        Instant updatedAt,
        String updatedBy
) {
}
