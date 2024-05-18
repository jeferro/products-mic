package com.jeferro.products.product_reviews.domain.events;

import java.time.Instant;

import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.shared.domain.events.Event;
import com.jeferro.products.shared.domain.events.EventId;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;

public abstract class ProductReviewEvent extends Event {

    private final ProductReviewId productReviewId;

    protected ProductReviewEvent(EventId id, ProductReviewId productReviewId, String occurredBy, Instant occurredOn) {
        super(id, occurredBy, occurredOn);

		validateProductReviewId(productReviewId);

		this.productReviewId = productReviewId;
    }

	public ProductReviewId getProductReviewId() {
        return productReviewId;
    }

	private static void validateProductReviewId(ProductReviewId productReviewId) {
		if (productReviewId == null) {
			throw ValueValidationException.createOfMessage("Product review identifier is null");
		}
	}
}
