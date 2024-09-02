package com.jeferro.products.products.products.domain.exceptions;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.exceptions.NotFoundException;

import static com.jeferro.products.products.products.domain.exceptions.ProductExceptionCodes.PRODUCT_NOT_FOUND;

public class ProductNotFoundException extends NotFoundException {

    private ProductNotFoundException(String message) {
        super(PRODUCT_NOT_FOUND.value, "Product not found", message);
    }

    public static ProductNotFoundException createOf(ProductCode productCode) {
        return new ProductNotFoundException("Product " + productCode + " not found");
    }
}
