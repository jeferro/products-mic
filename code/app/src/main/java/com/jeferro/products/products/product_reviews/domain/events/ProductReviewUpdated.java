package com.jeferro.products.products.product_reviews.domain.events;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.domain.events.EventId;
import lombok.Getter;

import java.util.Locale;

@Getter
public class ProductReviewUpdated extends ProductReviewEvent {

    private final Locale locale;

    private final String comment;

    private ProductReviewUpdated(EventId id,
                                 ProductReviewId productReviewId,
                                 Locale locale,
                                 String comment) {
        super(id, productReviewId);

        this.locale = locale;
        this.comment = comment;
    }

    public static ProductReviewUpdated create(ProductReview productReview) {
        var id = EventId.create();

        var productReviewId = productReview.getId();
        var locale = productReview.getLocale();
        var comment = productReview.getComment();

        return new ProductReviewUpdated(id, productReviewId, locale, comment);
    }
}
