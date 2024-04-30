package com.jeferro.products.products.infrastructure.integrations.mongo.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public record ProductMongoDTO(
        String id,
        String name
) { }