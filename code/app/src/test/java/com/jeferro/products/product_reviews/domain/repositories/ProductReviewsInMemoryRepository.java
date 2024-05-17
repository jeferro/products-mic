package com.jeferro.products.product_reviews.domain.repositories;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;

public class ProductReviewsInMemoryRepository extends InMemoryRepository<ProductReview, ProductReviewId>
	implements ProductReviewsRepository {

}