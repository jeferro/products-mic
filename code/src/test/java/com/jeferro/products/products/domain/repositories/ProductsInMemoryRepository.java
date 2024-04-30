package com.jeferro.products.products.domain.repositories;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;

public class ProductsInMemoryRepository extends InMemoryRepository<Product, ProductId>
        implements ProductsRepository {

    @Override
    public Products findAll() {
        var products = data.values().stream()
                .toList();

        return Products.createOf(products);
    }
}