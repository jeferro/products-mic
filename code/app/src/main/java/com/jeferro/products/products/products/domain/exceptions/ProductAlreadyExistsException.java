package com.jeferro.products.products.products.domain.exceptions;

import static com.jeferro.products.shared.domain.exceptions.ProductExceptionCodes.PRODUCT_ALREADY_EXISTS;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.exceptions.ConflictException;

public class ProductAlreadyExistsException extends ConflictException {

    private ProductAlreadyExistsException(String message) {
        super(PRODUCT_ALREADY_EXISTS, "Product already exists", message);
    }

    public static ProductAlreadyExistsException createOf(ProductCode productCode) {
        return new ProductAlreadyExistsException("Product " + productCode + " already exists");
    }
}
