package com.jeferro.products.products.domain.exceptions;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.shared.domain.exceptions.NotFoundException;

public class ProductNotFoundException extends NotFoundException {

    private ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException of(ProductId productId) {
        return new ProductNotFoundException("Product " + productId + " not found");
    }
}
