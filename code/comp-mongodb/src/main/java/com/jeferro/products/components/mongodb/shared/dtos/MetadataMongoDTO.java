package com.jeferro.products.components.mongodb.shared.dtos;

import java.time.Instant;

public record MetadataMongoDTO(
	String createdBy,
	Instant createdAt,
	String updatedBy,
	Instant updatedAt
) {

}
