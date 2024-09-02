package com.jeferro.products.products.product_reviews.domain.events;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.domain.events.EventId;

public class ProductReviewDeleted extends ProductReviewEvent {

    private ProductReviewDeleted(EventId id,
		ProductReviewId productReviewId) {
        super(id, productReviewId);
    }

    public static ProductReviewDeleted create(ProductReview productReview) {
	  var id = EventId.create();

	  var productReviewId = productReview.getId();

        return new ProductReviewDeleted(id, productReviewId);
    }
}
