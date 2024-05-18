package com.jeferro.products.shared.infrastructure.adapters.shared.mappers;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public abstract class ToDTOMapper<Entity, DTO> {

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
