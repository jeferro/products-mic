package com.jeferro.products.products.product_reviews.domain.models;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.models.aggregates.Identifier;
import com.jeferro.shared.ddd.domain.models.auth.Auth;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class ProductReviewId extends Identifier {

    private final String username;

    private final ProductCode productCode;

    public ProductReviewId(String username, ProductCode productCode) {
        this.username = username;
        this.productCode = productCode;
    }

    public static ProductReviewId createOf(Auth auth, ProductCode productCode) {
        ValueValidationUtils.isNotNull(auth, "auth", ProductReview.class);
        ValueValidationUtils.isNotNull(productCode, "productCode", ProductReview.class);

        String username = auth.username();

        return new ProductReviewId(username, productCode);
    }
}
