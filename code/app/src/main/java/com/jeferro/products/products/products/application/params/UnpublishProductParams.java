package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class UnpublishProductParams extends Params<Product> {

    private final ProductCode productCode;

    public UnpublishProductParams(ProductCode productCode) {
        super();

        ValueValidationUtils.isNotNull(productCode, "productCode", this);

        this.productCode = productCode;
    }
}
