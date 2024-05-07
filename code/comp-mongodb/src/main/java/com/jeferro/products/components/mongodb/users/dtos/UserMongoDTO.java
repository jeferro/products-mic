package com.jeferro.products.components.mongodb.users.dtos;

import java.util.Set;

import com.jeferro.products.components.mongodb.shared.dtos.MetadataMongoDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record UserMongoDTO(
        String id,
        String encodedPassword,
        Set<String> roles,
		MetadataMongoDTO metadata
) {
}