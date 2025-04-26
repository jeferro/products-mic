package com.jeferro.shared.mappers;

import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.ddd.domain.models.aggregates.StringIdentifier;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Map;

public abstract class AggregateMongoMapper<Aggregate extends AggregateRoot<Identifier>,
        Identifier extends StringIdentifier,
        DTO> {

    @Named("idToDomain")
    public abstract Identifier toDomain(String value);

    @Named("idToDTO")
    public String toDTO(Identifier id) {
        return id.getValue();
    }

    @Mapping(target = "id", qualifiedByName = "idToDomain")
    public abstract Aggregate toDomain(DTO dto);

    @Mapping(target = "id", qualifiedByName = "idToDTO")
    public abstract DTO toDTO(Aggregate entity);

    protected LocalizedField toDomain(Map<String, String> values) {
        return new LocalizedField(values);
    }

    protected Map<String, String> toDTO(LocalizedField entity) {
        return entity.getValues();
    }
}
