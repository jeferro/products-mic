package com.jeferro.products.components.mongodb.users.dtos;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record UserMongoDTO(
        String id,
        String encodedPassword,
        Set<String> roles
) {
}