package com.jeferro.products.products.domain.models;

import com.jeferro.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.domain.events.ProductUpdated;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.entities.AggregateRoot;
import org.apache.commons.lang3.StringUtils;

public class Product extends AggregateRoot<ProductId> {

    private String name;

    public Product(ProductId id, String name) {
        super(id);

        validateName(name);

        this.name = name;
    }

    public static Product create(String name, Auth auth) {
        var productId = ProductId.create();
        var product = new Product(productId, name);

        var event = ProductCreated.create(product, auth);
        product.record(event);

        return product;
    }

    public void update(String name, Auth auth) {
        validateName(name);

        this.name = name;

        var event = ProductUpdated.create(this, auth);
        record(event);
    }

    public void delete(Auth auth) {
        var event = ProductDeleted.create(this, auth);
        record(event);
    }

    private void validateName(String name) {
        if (StringUtils.isBlank(name)) {
            throw ValueValidationException.createOfMessage("Name is blank");
        }
    }

    public String getName() {
        return name;
    }
}
