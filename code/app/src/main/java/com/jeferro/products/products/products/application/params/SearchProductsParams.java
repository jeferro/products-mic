package com.jeferro.products.products.products.application.params;

import com.jeferro.products.products.products.domain.models.filter.ProductFilter;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.shared.ddd.application.params.Params;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import lombok.Getter;

@Getter
public class SearchProductsParams extends Params<Products> {

    private ProductFilter filter;

    public SearchProductsParams(ProductFilter filter) {
        super();

        setFilter(filter);
    }

    private void setFilter(ProductFilter filter) {
        ValueValidationUtils.isNotNull(filter, "filter", this);
        this.filter = filter;
    }
}
