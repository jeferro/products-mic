package com.jeferro.products.components.mongodb.product_reviews.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("product_reviews")
public record ProductReviewMongoDTO(
	String id,
	String username,
	String productId,
	String comment
) {


}
