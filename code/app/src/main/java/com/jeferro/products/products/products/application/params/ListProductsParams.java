package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;

public class ListProductsParams extends Params<Products> {

    private ProductCriteria criteria;

    public ListProductsParams(ProductCriteria criteria) {
        super();

        setCriteria(criteria);
    }

    public ProductCriteria getCriteria() {
        return criteria;
    }

    private void setCriteria(ProductCriteria criteria) {
        if (criteria == null) {
            throw ValueValidationException.createOfMessage("Criteria is null");
        }

        this.criteria = criteria;
    }
}
