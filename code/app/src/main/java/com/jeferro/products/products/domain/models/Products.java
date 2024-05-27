package com.jeferro.products.products.domain.models;

import com.jeferro.products.shared.domain.models.aggregates.EntityCollection;

import java.util.Arrays;
import java.util.List;

public class Products extends EntityCollection<ProductId, Product> {

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

    public static Products createOfCriteria(List<Product> entities, ProductCriteria criteria, Long totalEntities) {
        return new Products(entities, criteria.getPageSize(), criteria.getPageNumber(), totalEntities);
    }
}
