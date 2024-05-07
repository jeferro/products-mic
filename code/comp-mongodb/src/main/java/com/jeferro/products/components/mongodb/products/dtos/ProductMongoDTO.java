package com.jeferro.products.components.mongodb.products.dtos;

import com.jeferro.products.components.mongodb.shared.dtos.MetadataMongoDTO;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public record ProductMongoDTO(
        String id,
        String name,
		MetadataMongoDTO metadata
) { }