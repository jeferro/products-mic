package com.jeferro.products.shared.infrastructure.integrations.mappers;

import com.jeferro.products.shared.domain.models.identifiers.SimpleIdentifier;

import java.io.Serializable;

public abstract class SimpleIdentifierMapper<Identifier extends SimpleIdentifier<DTO>, DTO extends Serializable>
        extends BidirectionalMapper<Identifier, DTO> {

    @Override
    public DTO toDTO(Identifier identifier) {
        return identifier.getValue();
    }
}
