package com.jeferro.products.users.infrastructure.integrations.rest.dtos;

import com.jeferro.products.shared.infrastructure.integrations.rest.dtos.MetadataRestDTO;

import java.util.Set;

public record UserRestDTO(
        String id,
        Set<String> roles,
        MetadataRestDTO metadata
) {
}
