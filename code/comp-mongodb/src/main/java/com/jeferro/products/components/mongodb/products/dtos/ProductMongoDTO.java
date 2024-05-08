package com.jeferro.products.components.mongodb.products.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public record ProductMongoDTO(
        String id,
        String name
) { }