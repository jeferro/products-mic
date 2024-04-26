package com.jeferro.products.products.infrastructure.integrations.mongo.dtos;

import com.jeferro.products.shared.infrastructure.integrations.mongo.dtos.MetadataMongoDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public record ProductMongoDTO(
        String id,
        String name,
        MetadataMongoDTO metadata
) { }