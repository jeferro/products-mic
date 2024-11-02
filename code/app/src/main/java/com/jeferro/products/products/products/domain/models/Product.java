package com.jeferro.products.products.products.domain.models;

import static com.jeferro.products.products.products.domain.models.ProductStatus.PUBLISHED;
import static com.jeferro.products.products.products.domain.models.ProductStatus.UNPUBLISHED;

import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.products.domain.events.ProductPublished;
import com.jeferro.products.products.products.domain.events.ProductUnpublished;
import com.jeferro.products.products.products.domain.events.ProductUpdated;
import com.jeferro.products.products.products.domain.models.product_types.ProductTypeId;
import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;

public class Product extends AggregateRoot<ProductCode> {

    private LocalizedField name;

    private ProductStatus status;

    private ProductTypeId typeId;

    public Product(ProductCode id,
        ProductTypeId typeId,
        LocalizedField name,
        ProductStatus status) {
        super(id);

        setName(name);
        setTypeId(typeId);
        setStatus(status);
    }

    public static Product create(ProductCode productCode,
        ProductTypeId typeId,
        LocalizedField name) {
        var product = new Product(productCode, typeId, name, UNPUBLISHED);

        var event = ProductCreated.create(product);
        product.record(event);

        return product;
    }

    public void update(LocalizedField name) {
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

    public LocalizedField getName() {
        return name;
    }

    private void setName(LocalizedField name) {
        ValueValidationUtils.isNotNull(name, "Name");
        this.name = name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    private void setStatus(ProductStatus status) {
        ValueValidationUtils.isNotNull(status, "Status");
        this.status = status;
    }

    public ProductTypeId getTypeId() {
        return typeId;
    }

    public void setTypeId(ProductTypeId typeId) {
        ValueValidationUtils.isNotNull(typeId, "Type id");
        this.typeId = typeId;
    }
}
