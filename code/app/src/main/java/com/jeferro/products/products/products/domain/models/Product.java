package com.jeferro.products.products.products.domain.models;

import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.products.domain.events.ProductUpdated;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.domain.models.auth.Auth;
import org.apache.commons.lang3.StringUtils;

public class Product extends AggregateRoot<ProductCode> {

    private String name;

    public Product(ProductCode id, String name) {
        super(id);

        setName(name);
    }

    public static Product create(ProductCode productCode, String name, Auth auth) {
        var product = new Product(productCode, name);

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

    public ProductCode getCode() {
        return id;
    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw ValueValidationException.createOfMessage("Name is blank");
        }

        this.name = name;
    }
}
