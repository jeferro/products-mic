package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class ListProductsParams extends Params<Products> {

    private ProductCriteria criteria;

    public ListProductsParams(ProductCriteria criteria) {
        super();

        setCriteria(criteria);
    }

    private void setCriteria(ProductCriteria criteria) {
        ValueValidationUtils.isNotNull(criteria, "Criteria");
        this.criteria = criteria;
    }
}
