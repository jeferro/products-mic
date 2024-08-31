package com.jeferro.products.products.products.domain.models;

import static com.jeferro.products.products.products.domain.models.ProductStatus.UNPUBLISHED;

import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.products.domain.events.ProductUpdated;
import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.aggregates.AggregateRoot;
import org.apache.commons.lang3.StringUtils;

public class Product extends AggregateRoot<ProductCode> {

    private String name;

    private ProductStatus status;

    public Product(ProductCode id, String name, ProductStatus status) {
        super(id);

        setName(name);
        setStatus(status);
    }

    public static Product create(ProductCode productCode, String name) {
        var product = new Product(productCode, name, UNPUBLISHED);

        var event = ProductCreated.create(product);
        product.record(event);

        return product;
    }

    public void update(String name) {
        setName(name);

        var event = ProductUpdated.create(this);
        record(event);
    }

    public void updateStatus(ProductStatus status) {
        if(this.status == status) {
            return;
        }

        setStatus(status);

        var event = ProductUpdated.create(this);
        record(event);
    }

    public void delete() {
        var event = ProductDeleted.create(this);
        record(event);
    }

    public ProductCode getCode() {
        return id;
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

    public ProductStatus getStatus() {
        return status;
    }

    private void setStatus(ProductStatus status) {
        if (status == null) {
            throw ValueValidationException.createOfMessage("Status is null");
        }

        this.status = status;
    }
}
