package com.jeferro.products.products.domain.models;

import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.aggregates.AggregateRoot;
import org.apache.commons.lang3.StringUtils;

public class Product extends AggregateRoot<ProductId> {

    private String name;

    public Product(ProductId id, String name) {
        super(id);

        setName(name);
    }

    public static Product create(String name, Auth auth) {
        var productId = ProductId.create();
        var product = new Product(productId, name);

        var event = ProductCreated.create(product, auth);
        product.record(event);

        return product;
    }

    public void update(String name, Auth auth) {
        setName(name);

        var event = ProductUpdated.create(this, auth);
        record(event);
    }

    public void delete(Auth auth) {
        var event = ProductDeleted.create(this, auth);
        record(event);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw ValueValidationException.createOfMessage("Name is blank");
        }

        this.name = name;
    }
}
