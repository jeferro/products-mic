package com.jeferro.products.products.products.domain.repositories;

import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.filter.ProductFilter;
import com.jeferro.products.products.products.domain.models.Products;
import com.jeferro.products.shared.domain.repositories.InMemoryRepository;

public class ProductsInMemoryRepository extends InMemoryRepository<Product, ProductCode>
        implements ProductsRepository {

    @Override
    public ProductCode nextId() {
        return new ProductCode("1");
    }

    @Override
    public Products findAll(ProductFilter filter) {
        var entities = data.values().stream()
                .filter(product -> matchProduct(filter, product))
                .toList();

        var paginatedEntities = paginateEntities(entities, filter);

        return new Products(paginatedEntities);
    }

    private boolean matchProduct(ProductFilter filter, Product product) {
        return matchProductName(filter, product);
    }

    private boolean matchProductName(ProductFilter filter, Product product) {
        return !filter.hasName()
                || product.getName().containsValue(filter.getName());
    }
}