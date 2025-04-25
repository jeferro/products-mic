package com.jeferro.products.products.product_reviews.domain.repositories;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;

import java.util.List;

public class ProductReviewsInMemoryRepository extends InMemoryRepository<ProductReview, ProductReviewId>
        implements ProductReviewsRepository {

    @Override
    public ProductReviews findAllByProductCode(ProductCode productCode) {
        var products = data.values().stream()
                .filter(productReview -> productReview.getProductCode().equals(productCode))
                .toList();

        return new ProductReviews(products);
    }

    @Override
    public void deleteAllById(List<ProductReviewId> productReviewIds) {
        productReviewIds.forEach(this::deleteById);
    }
}