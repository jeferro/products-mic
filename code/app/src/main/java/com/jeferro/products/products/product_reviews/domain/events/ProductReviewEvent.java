package com.jeferro.products.products.product_reviews.domain.events;

import java.time.Instant;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.domain.events.Event;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;

public abstract class ProductReviewEvent extends Event {

    private ProductReviewId productReviewId;

    protected ProductReviewEvent(EventId id, ProductReviewId productReviewId, String occurredBy, Instant occurredOn) {
        super(id, occurredBy, occurredOn);

		setProductReviewId(productReviewId);
    }

	public ProductReviewId getProductReviewId() {
        return productReviewId;
    }

	private void setProductReviewId(ProductReviewId productReviewId) {
		if (productReviewId == null) {
			throw ValueValidationException.createOfMessage("Product review identifier is null");
		}

	  	this.productReviewId = productReviewId;
	}
}
