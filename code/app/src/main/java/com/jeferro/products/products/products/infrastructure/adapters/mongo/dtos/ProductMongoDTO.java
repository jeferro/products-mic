package com.jeferro.products.products.products.infrastructure.adapters.mongo.dtos;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public record ProductMongoDTO(
        String id,
		ProductStatusMongoDTO status,
        Map<String, String> name
) { }