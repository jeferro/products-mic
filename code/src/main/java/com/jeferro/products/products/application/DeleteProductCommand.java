package com.jeferro.products.products.application;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.application.Command;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;

public class DeleteProductCommand extends Command<Product> {

    private final ProductId productId;

    public DeleteProductCommand(Auth auth, ProductId productId) {
        super(auth);

        if (productId == null) {
            throw ValueValidationException.ofMessage("Product identifier is null");
        }

        this.productId = productId;
    }

    public ProductId getProductId() {
        return productId;
    }
}
