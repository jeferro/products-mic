package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class UpdateProductParams extends Params<Product> {

    private final ProductCode productCode;

    private final LocalizedField name;

    public UpdateProductParams(ProductCode productCode, LocalizedField name) {
        super();

        ValueValidationUtils.isNotNull(productCode, "productCode", this);
        ValueValidationUtils.isNotNull(name, "name", this);

        this.productCode = productCode;
        this.name = name;
    }
}
