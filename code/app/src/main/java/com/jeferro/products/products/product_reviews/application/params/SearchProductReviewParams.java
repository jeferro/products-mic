package com.jeferro.products.products.product_reviews.application.params;

import com.jeferro.products.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class SearchProductReviewParams extends Params<ProductReviews> {

    private final ProductCode productCode;

    public SearchProductReviewParams(ProductCode productCode) {
        super();

        ValueValidationUtils.isNotNull(productCode, "productCode", this);

        this.productCode = productCode;
    }

}
