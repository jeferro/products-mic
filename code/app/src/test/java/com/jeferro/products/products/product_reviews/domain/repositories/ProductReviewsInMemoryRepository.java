package com.jeferro.products.products.product_reviews.domain.repositories;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;
import com.jeferro.shared.ddd.domain.models.aggregates.Entity;
import com.jeferro.shared.ddd.domain.models.aggregates.PaginatedList;

public class ProductReviewsInMemoryRepository extends InMemoryRepository<ProductReview, ProductReviewId>
        implements ProductReviewsRepository {

    @Override
    public PaginatedList<ProductReview> findAllByProductCode(ProductCode productCode) {
        var products = data.values().stream()
                .filter(productReview -> productReview.getProductCode().equals(productCode))
                .toList();

        return PaginatedList.createOfList(products);
    }

    @Override
    public void deleteAll(PaginatedList<ProductReview> productReviews) {
        productReviews.stream()
                .map(Entity::getId)
                .forEach(this::deleteById);
    }
}