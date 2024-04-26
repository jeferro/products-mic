package com.jeferro.products.products.infrastructure.integrations.rest.dtos;

import com.jeferro.products.shared.infrastructure.integrations.rest.dtos.MetadataRestDTO;

public record ProductRestDTO(
        String id,
        String name,
        MetadataRestDTO metadata
) {
}
