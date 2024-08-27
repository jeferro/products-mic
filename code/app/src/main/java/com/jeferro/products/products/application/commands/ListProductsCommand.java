package com.jeferro.products.products.application.commands;

import com.jeferro.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.shared.application.commands.Command;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.auth.Auth;

public class ListProductsCommand extends Command<Products> {

    private ProductCriteria productCriteria;

    public ListProductsCommand(Auth auth, ProductCriteria productCriteria) {
        super(auth);

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
