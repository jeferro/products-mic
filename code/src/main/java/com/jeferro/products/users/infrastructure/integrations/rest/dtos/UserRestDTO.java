package com.jeferro.products.users.infrastructure.integrations.rest.dtos;

import java.util.Set;

public record UserRestDTO(
        String id,
        Set<String> roles
) {
}
