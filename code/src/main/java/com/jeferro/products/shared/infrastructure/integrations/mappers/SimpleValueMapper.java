package com.jeferro.products.shared.infrastructure.integrations.mappers;

import com.jeferro.products.shared.domain.models.values.SimpleValue;

import java.io.Serializable;

public abstract class SimpleValueMapper<Value extends SimpleValue<DTO>, DTO extends Serializable>
        extends BidirectionalMapper<Value, DTO> {

    @Override
    public DTO toDTO(Value value) {
        return value.getValue();
    }
}
