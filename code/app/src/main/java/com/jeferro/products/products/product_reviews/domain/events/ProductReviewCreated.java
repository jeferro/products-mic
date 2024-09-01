package com.jeferro.products.products.product_reviews.domain.events;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.domain.events.EventId;

public class ProductReviewCreated extends ProductReviewEvent {

    private ProductReviewCreated(EventId id,
		ProductReviewId productReviewId) {
        super(id, productReviewId);
    }

    public static ProductReviewCreated create(ProductReview productReview) {
        var productReviewId = productReview.getId();

		var id = EventId.create();

        return new ProductReviewCreated(id, productReviewId);
    }
}
