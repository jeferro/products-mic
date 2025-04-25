package com.jeferro.products.products.products.domain.events;

import com.jeferro.products.products.products.domain.models.ProductCode;
import com.jeferro.shared.ddd.domain.events.Event;
import com.jeferro.shared.ddd.domain.events.EventId;
import lombok.Getter;

@Getter
public abstract class ProductEvent extends Event {

    private final ProductCode code;

    protected ProductEvent(EventId id, ProductCode code) {
        super(id);

        this.code = code;
    }
}
