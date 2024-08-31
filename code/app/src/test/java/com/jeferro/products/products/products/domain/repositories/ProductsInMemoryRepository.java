package com.jeferro.products.products.products.domain.repositories;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.ProductCriteria;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;

public class ProductsInMemoryRepository extends InMemoryRepository<Product, ProductCode>
        implements ProductsRepository {

    @Override
    public Products findAll(ProductCriteria criteria) {
        var entities = data.values().stream()
                .filter(product -> matchProduct(criteria, product))
                .toList();

        var paginatedEntities = paginateEntities(entities, criteria);

        return new Products(paginatedEntities);
    }

    private boolean matchProduct(ProductCriteria criteria, Product product) {
        return matchProductName(criteria, product);
    }

    private boolean matchProductName(ProductCriteria criteria, Product product) {
        return criteria.hasNotName()
                || product.getName().containsValue(criteria.getName());
    }
}