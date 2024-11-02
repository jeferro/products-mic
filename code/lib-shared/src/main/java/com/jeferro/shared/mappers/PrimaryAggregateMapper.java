package com.jeferro.shared.mappers;

import java.util.List;
import java.util.Map;

import com.jeferro.shared.auth.infrastructure.ContextManager;
import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.ddd.domain.models.aggregates.EntityCollection;
import com.jeferro.shared.ddd.domain.models.aggregates.StringIdentifier;
import com.jeferro.shared.locale.domain.models.LocalizedField;

public abstract class PrimaryAggregateMapper<T extends AggregateRoot<ID>,
	ID extends StringIdentifier,
	DTO> {

  public abstract DTO toDTO(T entity);

  protected LocalizedField toDomain(Map<String, String> values) {
	return new LocalizedField(values);
  }

  protected Map<String, String> toDTO(LocalizedField entity) {
	var locale = ContextManager.getLocale();

	return entity.getValues(locale);
  }

  public List<DTO> toDTO(EntityCollection<ID, T> entities) {
	return entities.map(this::toDTO).toList();
  }
}