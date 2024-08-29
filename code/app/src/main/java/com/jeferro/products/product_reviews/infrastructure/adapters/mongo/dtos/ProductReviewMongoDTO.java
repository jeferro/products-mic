package com.jeferro.products.product_reviews.infrastructure.adapters.mongo.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("product_reviews")
public record ProductReviewMongoDTO(
	String id,
	String username,
	String productCode,
	String comment
) {


}
