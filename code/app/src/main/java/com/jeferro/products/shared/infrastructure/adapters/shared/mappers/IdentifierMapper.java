package com.jeferro.products.shared.infrastructure.adapters.shared.mappers;

import java.io.Serializable;

import com.jeferro.products.shared.domain.models.aggregates.Identifier;

public abstract class IdentifierMapper<ID extends Identifier<DTO>, DTO extends Serializable>
	extends BidirectionalMapper<ID, DTO> {

	@Override
	public DTO toDTO(ID id) {
		return id.getValue();
	}

	@Override
	public abstract ID toDomain(DTO value);
}
