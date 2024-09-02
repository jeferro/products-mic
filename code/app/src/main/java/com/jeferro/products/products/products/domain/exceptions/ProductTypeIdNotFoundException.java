package com.jeferro.products.products.products.domain.exceptions;

import com.jeferro.products.products.products.domain.models.ProductTypeId;
import com.jeferro.shared.ddd.domain.exceptions.NotFoundException;

public class ProductTypeIdNotFoundException extends NotFoundException {

    private ProductTypeIdNotFoundException(String message) {
        super(ProductExceptionCodes.PRODUCT_TYPE_NOT_FOUND.value, "Product type not found", message);
    }

    public static ProductTypeIdNotFoundException createOf(ProductTypeId productTypeId) {
        return new ProductTypeIdNotFoundException("Product type " + productTypeId + " not found");
    }
}
