package com.jeferro.products.products.domain.models;

import com.jeferro.products.shared.domain.models.entities.EntityCollection;

import java.util.Arrays;
import java.util.List;

public class Products extends EntityCollection<ProductId, Product> {

    protected Products(List<Product> entities) {
        super(entities);
    }

    public static Products of(List<Product> entities) {
        return new Products(entities);
    }

    public static Products of(Product... products) {
        var entities = Arrays.asList(products);
        return new Products(entities);
    }
}
