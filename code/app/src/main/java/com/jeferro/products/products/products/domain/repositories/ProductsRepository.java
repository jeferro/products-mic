package com.jeferro.products.products.products.domain.repositories;

import com.jeferro.products.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.filter.ProductFilter;
import com.jeferro.shared.ddd.domain.models.aggregates.PaginatedList;

import java.util.Optional;

public interface ProductsRepository {

    ProductCode nextId();

    void save(Product product);

    Optional<Product> findById(ProductCode productCode);

    default Product findByIdOrError(ProductCode productCode) {
        return findById(productCode)
                .orElseThrow(() -> ProductNotFoundException.createOf(productCode));
    }

    void deleteById(ProductCode productCode);

    PaginatedList<Product> findAll(ProductFilter filter);
}
