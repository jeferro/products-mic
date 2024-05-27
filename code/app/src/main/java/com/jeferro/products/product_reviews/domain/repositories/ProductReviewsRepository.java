package com.jeferro.products.product_reviews.domain.repositories;

import com.jeferro.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.domain.models.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductReviewsRepository {

    void save(ProductReview productReview);

    ProductReviews findAllByProductId(ProductId productId);

    Optional<ProductReview> findById(ProductReviewId productReviewId);

    default ProductReview findByIdOrError(ProductReviewId productReviewId) {
        return findById(productReviewId)
                .orElseThrow(() -> ProductReviewNotFoundException.createOf(productReviewId));
    }

    void deleteById(ProductReviewId productReviewId);

    void deleteAllById(List<ProductReviewId> productReviewIds);
}
