package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.parametrics.domain.models.values.ParametricValueId;
import com.jeferro.products.products.products.domain.models.Product;
import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.products.products.products.domain.models.status.ProductStatus;
import com.jeferro.shared.ddd.domain.events.EventId;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import lombok.Getter;

@Getter
public class ProductCreated extends ProductEvent {

    private final LocalizedField name;

    private final ParametricValueId typeId;

    private final ProductStatus status;

    private ProductCreated(EventId id,
                           ProductCode code,
                           LocalizedField name,
                           ParametricValueId typeId,
                           ProductStatus status) {
        super(id, code);

        this.name = name;
        this.typeId = typeId;
        this.status = status;
    }

    public static ProductCreated create(Product product) {
        var id = EventId.create();

        var code = product.getCode();
        var typeId = product.getTypeId();
        var name = product.getName();
        var status = product.getStatus();

        return new ProductCreated(id, code, name, typeId, status);
    }
}
