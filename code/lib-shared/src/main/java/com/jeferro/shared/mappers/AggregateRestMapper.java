package com.jeferro.shared.mappers;

import com.jeferro.shared.auth.infrastructure.ContextManager;
import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.ddd.domain.models.aggregates.PaginatedList;
import com.jeferro.shared.ddd.domain.models.aggregates.StringIdentifier;
import com.jeferro.shared.locale.domain.models.LocalizedField;

import java.util.List;
import java.util.Map;

public abstract class AggregateRestMapper<Aggregate extends AggregateRoot<Identifier>,
        Identifier extends StringIdentifier,
        DTO> {

    public abstract DTO toDTO(Aggregate entity);

    public abstract List<DTO> toDTO(PaginatedList<Aggregate> entities);

    protected LocalizedField toDomain(Map<String, String> values) {
        return new LocalizedField(values);
    }

    protected Map<String, String> toDTO(LocalizedField entity) {
        var locale = ContextManager.getLocale();

        return entity.getValues(locale);
    }
}
