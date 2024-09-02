package com.jeferro.products.products.products.domain.exceptions;

import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;
import com.jeferro.shared.ddd.domain.exceptions.NotFoundException;

public class ProductTypeNotFoundException extends NotFoundException {

    private ProductTypeNotFoundException(String message) {
        super(ProductExceptionCodes.PRODUCT_TYPE_NOT_FOUND.value, "Product type not found", message);
    }

    public static ProductTypeNotFoundException createOf(ProductTypeId productTypeId) {
        return new ProductTypeNotFoundException("Product type " + productTypeId + " not found");
    }
}
