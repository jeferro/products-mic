package com.jeferro.products.product_reviews.domain.events;

import java.time.Instant;

import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.shared.domain.events.EventId;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.services.time.TimeService;

public class ProductReviewUpdated extends ProductReviewEvent {

    private ProductReviewUpdated(EventId id, ProductReviewId productReviewId, String occurredBy, Instant occurredOn) {
        super(id, productReviewId, occurredBy, occurredOn);
    }

    public static ProductReviewUpdated create(ProductReview productReview, Auth auth) {
        var productReviewId = productReview.getId();

		var id = EventId.create();
		var occurredBy = auth.who();
		var occurredOn = TimeService.now();

        return new ProductReviewUpdated(id, productReviewId, occurredBy, occurredOn);
    }
}
