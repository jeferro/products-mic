package com.jeferro.shared.infrastructure.mappers;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public abstract class ToDomainMapper<Entity, DTO> {

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
}
