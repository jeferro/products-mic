package com.jeferro.products.products.products.domain.models;

import static com.jeferro.products.products.products.domain.models.ProductStatus.PUBLISHED;
import static com.jeferro.products.products.products.domain.models.ProductStatus.UNPUBLISHED;

import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.products.domain.events.ProductPublished;
import com.jeferro.products.products.products.domain.events.ProductUnpublished;
import com.jeferro.products.products.products.domain.events.ProductUpdated;
import com.jeferro.shared.ddd.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.locale.domain.models.LocalizedData;

public class Product extends AggregateRoot<ProductCode> {

    private LocalizedData name;

    private ProductStatus status;

    public Product(ProductCode id, LocalizedData name, ProductStatus status) {
        super(id);

        setName(name);
        setStatus(status);
    }

    public static Product create(ProductCode productCode, LocalizedData name) {
        var product = new Product(productCode, name, UNPUBLISHED);

        var event = ProductCreated.create(product);
        product.record(event);

        return product;
    }

    public void update(LocalizedData name) {
        setName(name);

        var event = ProductUpdated.create(this);
        record(event);
    }

    public void updateStatus(ProductStatus status) {
        if(this.status == status) {
            return;
        }

        setStatus(status);

        if (PUBLISHED.equals(status)) {
            var event = ProductPublished.create(this);
            record(event);
        }
        else {
			var event = ProductUnpublished.create(this);
			record(event);
        }
    }

    public void delete() {
        var event = ProductDeleted.create(this);
        record(event);
    }

    public ProductCode getCode() {
        return id;
    }

    public LocalizedData getName() {
        return name;
    }

    private void setName(LocalizedData name) {
        if (name == null) {
            throw ValueValidationException.createOfMessage("Name is null");
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
