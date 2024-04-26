package com.jeferro.products.users.infrastructure.integrations.rest.dtos;

public record SignInRestDTO(
        String username,
        String password
) {
}
