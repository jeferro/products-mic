package com.jeferro.products.products.product_reviews.infrastructure.mongo.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("product_reviews")
public record ProductReviewMongoDTO(
	ProductReviewIdMongoDTO id,
	String username,
	String productCode,
	String locale,
	String comment
) {
	public String getId() {
	  return username + "::" + productCode;
	}
}
