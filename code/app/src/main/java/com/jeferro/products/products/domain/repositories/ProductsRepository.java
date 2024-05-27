package com.jeferro.products.products.domain.repositories;

import java.util.Optional;

import com.jeferro.products.products.domain.exceptions.ProductNotFoundException;
import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.domain.models.Products;

public interface ProductsRepository {

    void save(Product product);

    Optional<Product> findById(ProductId productId);

    default Product findByIdOrError(ProductId productId) {
        return findById(productId)
            .orElseThrow(() -> ProductNotFoundException.createOf(productId));
    }

    void deleteById(ProductId productId);

    Products findAll(ProductCriteria productCriteria);
}
