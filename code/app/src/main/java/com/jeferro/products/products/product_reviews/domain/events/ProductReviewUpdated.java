package com.jeferro.products.products.product_reviews.domain.events;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.domain.events.EventId;

public class ProductReviewUpdated extends ProductReviewEvent {

    private ProductReviewUpdated(EventId id,
		ProductReviewId productReviewId) {
        super(id, productReviewId);
    }

    public static ProductReviewUpdated create(ProductReview productReview) {
        var productReviewId = productReview.getId();

		var id = EventId.create();

        return new ProductReviewUpdated(id, productReviewId);
    }
}
