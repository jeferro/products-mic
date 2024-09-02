package com.jeferro.products.products.product_reviews.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.product-reviews")
public record ProductReviewsProperties(
		String topic
) {

}
