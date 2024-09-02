package com.jeferro.products.products.products.domain.exceptions;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.exceptions.ConstraintException;

public class ProductAlreadyExistsException extends ConstraintException {

    private ProductAlreadyExistsException(String message) {
        super(ProductExceptionCodes.PRODUCT_ALREADY_EXISTS.value, "Product already exists", message);
    }

    public static ProductAlreadyExistsException createOf(ProductCode productCode) {
        return new ProductAlreadyExistsException("Product " + productCode + " already exists");
    }
}
