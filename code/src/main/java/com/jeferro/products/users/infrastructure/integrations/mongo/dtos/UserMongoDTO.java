package com.jeferro.products.users.infrastructure.integrations.mongo.dtos;

import com.jeferro.products.shared.infrastructure.integrations.mongo.dtos.MetadataMongoDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
public record UserMongoDTO(
        String id,
        String encodedPassword,
        Set<String> roles,
        MetadataMongoDTO metadata
) {
}