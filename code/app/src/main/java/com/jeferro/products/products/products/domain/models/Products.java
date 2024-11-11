package com.jeferro.products.products.products.domain.models;

import com.jeferro.products.products.products.domain.models.filter.ProductFilter;
import com.jeferro.shared.ddd.domain.models.aggregates.EntityCollection;

import java.util.Arrays;
import java.util.List;

public class Products extends EntityCollection<ProductCode, Product> {

    public Products() {
        super();
    }

    public Products(List<Product> entities) {
        super(entities);
    }

    public Products(List<Product> entities, Integer pageSize, Integer pageNumber, Long totalPages) {
        super(entities, pageSize, pageNumber, totalPages);
    }

    public static Products createOf(Product... products) {
        var entities = Arrays.asList(products);
        return new Products(entities);
    }

    public static Products createOfFilter(List<Product> products, ProductFilter filter, Long totalEntities) {
        return new Products(products,
            filter.getPageSize(),
            filter.getPageNumber(),
            totalEntities);
    }
}
