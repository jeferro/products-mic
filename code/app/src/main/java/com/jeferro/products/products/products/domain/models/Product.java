package com.jeferro.products.products.products.domain.models;

import static com.jeferro.products.products.products.domain.models.ProductStatus.PUBLISHED;
import static com.jeferro.products.products.products.domain.models.ProductStatus.UNPUBLISHED;

import com.jeferro.products.parametrics.domain.models.ParametricValueId;
import com.jeferro.products.products.products.domain.events.ProductCreated;
import com.jeferro.products.products.products.domain.events.ProductDeleted;
import com.jeferro.products.products.products.domain.events.ProductPublished;
import com.jeferro.products.products.products.domain.events.ProductUnpublished;
import com.jeferro.products.products.products.domain.events.ProductUpdated;
import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.ddd.domain.utils.ValueValidationUtils;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class Product extends AggregateRoot<ProductCode> {

    private LocalizedField name;

    private ParametricValueId typeId;

    private ProductStatus status;

    public Product(ProductCode id,
        LocalizedField name,
        ParametricValueId typeId,
        ProductStatus status) {
        super(id);

        setName(name);
        setTypeId(typeId);
        setStatus(status);
    }

    public static Product create(ProductCode productCode,
        ParametricValueId typeId,
        LocalizedField name) {
        var product = new Product(productCode, name, typeId, UNPUBLISHED);

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

    private void setName(LocalizedField name) {
        ValueValidationUtils.isNotNull(name, "name", this);
        this.name = name;
    }

    private void setStatus(ProductStatus status) {
        ValueValidationUtils.isNotNull(status, "status", this);
        this.status = status;
    }

    public void setTypeId(ParametricValueId typeId) {
        ValueValidationUtils.isNotNull(typeId, "typeId", this);
        this.typeId = typeId;
    }
}
