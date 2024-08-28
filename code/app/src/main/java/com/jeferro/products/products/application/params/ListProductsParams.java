package com.jeferro.products.products.application.params;

import com.jeferro.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.shared.application.Params;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;

public class ListProductsParams extends Params<Products> {

    private ProductCriteria productCriteria;

    public ListProductsParams(ProductCriteria productCriteria) {
        super();

        setProductCriteria(productCriteria);
    }

    public ProductCriteria getProductCriteria() {
        return productCriteria;
    }

    private void setProductCriteria(ProductCriteria productCriteria) {
        if (productCriteria == null) {
            throw ValueValidationException.createOfMessage("Criteria is null");
        }

        this.productCriteria = productCriteria;
    }
}