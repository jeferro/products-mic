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

    private ProductTypeId typeId;

    public Product(ProductCode id,
        ProductTypeId typeId,
        LocalizedData name,
        ProductStatus status) {
        super(id);

        setName(name);
        setTypeId(typeId);
        setStatus(status);
    }

    public static Product create(ProductCode productCode,
        ProductTypeId typeId,
        LocalizedData name) {
        var product = new Product(productCode, typeId, name, UNPUBLISHED);

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

    public ProductTypeId getTypeId() {
        return typeId;
    }

    public void setTypeId(ProductTypeId typeId) {
        if (typeId == null) {
            throw ValueValidationException.createOfMessage("Type id is null");
        }

        this.typeId = typeId;
    }
}
