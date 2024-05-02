package com.jeferro.products.shared.infrastructure.adapters.shared.mappers;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public abstract class BidirectionalMapper<Entity, DTO> {

    public abstract Entity toDomain(DTO dto);

    public List<Entity> toDomainList(List<DTO> dtos) {
        return dtos.stream()
                .map(this::toDomain)
                .collect(toList());
    }

    public Set<Entity> toDomainSet(Set<DTO> dtos) {
        return dtos.stream()
                .map(this::toDomain)
                .collect(toSet());

    }

    public abstract DTO toDTO(Entity entity);

    public List<DTO> toDTOList(List<Entity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(toList());
    }

    public Set<DTO> toDTOSet(Set<Entity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(toSet());
    }
}
