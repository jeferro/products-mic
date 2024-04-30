package com.jeferro.products.shared.infrastructure.integrations.mappers;

import com.jeferro.products.shared.domain.models.value_objects.SimpleValueObject;

import java.io.Serializable;

public abstract class SimpleValueMapper<Value extends SimpleValueObject<DTO>, DTO extends Serializable>
        extends BidirectionalMapper<Value, DTO> {

    @Override
    public DTO toDTO(Value value) {
        return value.getValue();
    }
}
