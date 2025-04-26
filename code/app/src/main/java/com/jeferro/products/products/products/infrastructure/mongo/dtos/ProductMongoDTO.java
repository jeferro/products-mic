package com.jeferro.products.products.products.infrastructure.mongo.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "products")
public record ProductMongoDTO(
        String id,
        String typeId,
        ProductStatusMongoDTO status,
        Map<String, String> name
) {
}