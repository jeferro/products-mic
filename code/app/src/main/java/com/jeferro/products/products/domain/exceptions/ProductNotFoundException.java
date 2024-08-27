package com.jeferro.products.products.domain.exceptions;

import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.shared.domain.exceptions.NotFoundException;

import static com.jeferro.products.products.domain.exceptions.ProductExceptionCodes.PRODUCT_NOT_FOUND;

public class ProductNotFoundException extends NotFoundException {

    private ProductNotFoundException(String message) {
        super(PRODUCT_NOT_FOUND.value, "Product not found", message);
    }

    public static ProductNotFoundException createOf(ProductId productId) {
        return new ProductNotFoundException("Product " + productId + " not found");
    }
}
