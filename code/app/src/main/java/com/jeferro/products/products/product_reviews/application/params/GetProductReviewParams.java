package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class GetProductReviewParams extends Params<ProductReview> {

    private final ProductReviewId productReviewId;

    public GetProductReviewParams(ProductReviewId productReviewId) {
        super();

        ValueValidationUtils.isNotNull(productReviewId, "productReviewId", this);

        this.productReviewId = productReviewId;
    }

}
