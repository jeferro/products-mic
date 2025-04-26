package com.jeferro.products.products.product_reviews.domain.events;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.events.Event;
import com.jeferro.shared.ddd.domain.events.EventId;
import lombok.Getter;

@Getter
public abstract class ProductReviewEvent extends Event {

    private final ProductReviewId productReviewId;

    protected ProductReviewEvent(EventId id,
                                 ProductReviewId productReviewId) {
        super(id);

        this.productReviewId = productReviewId;
    }

    public ProductCode getProductCode() {
        return productReviewId.getProductCode();
    }

    public String getUsername() {
        return productReviewId.getUsername();
    }
}
