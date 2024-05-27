package com.jeferro.products.products.domain.repositories;

import com.jeferro.products.products.domain.models.Product;
import com.jeferro.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.domain.models.Products;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;

public class ProductsInMemoryRepository extends InMemoryRepository<Product, ProductId>
        implements ProductsRepository {

    @Override
    public Products findAll(ProductCriteria criteria) {
        var products = data.values().stream()
                .filter(product -> matchProduct(criteria, product))
                .toList();

        return new Products(products);
    }

    private boolean matchProduct(ProductCriteria criteria, Product product) {
        return matchProductName(criteria, product);
    }

    private boolean matchProductName(ProductCriteria criteria, Product product) {
        return criteria.hasNotName()
                || product.getName().toLowerCase().contains(criteria.getName());
    }
}