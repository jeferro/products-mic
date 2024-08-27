package com.jeferro.shared.infrastructure.mappers;

import java.io.Serializable;

import com.jeferro.shared.domain.models.value_objects.SimpleValueObject;
import org.mapstruct.Mapping;

public abstract class SimpleValueMapper<Value extends SimpleValueObject<DTO>, DTO extends Serializable>
        extends BidirectionalMapper<Value, DTO> {

    @Override
    public DTO toDTO(Value value) {
        return value.getValue();
    }

	@Override
	@Mapping(source = "dto", target = "value")
	public abstract Value toDomain(DTO dto);
}
