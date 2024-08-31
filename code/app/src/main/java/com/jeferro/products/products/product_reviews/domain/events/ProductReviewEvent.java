package com.jeferro.products.products.product_reviews.domain.events;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.domain.events.Event;
import com.jeferro.shared.domain.events.EventId;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;

public abstract class ProductReviewEvent extends Event {

    private ProductReviewId productReviewId;

    protected ProductReviewEvent(EventId id,
		ProductReviewId productReviewId) {
        super(id);

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
