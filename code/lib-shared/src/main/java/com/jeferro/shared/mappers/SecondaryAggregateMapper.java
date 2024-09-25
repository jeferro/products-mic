package com.jeferro.shared.mappers;

import java.util.Map;

import com.jeferro.shared.ddd.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.ddd.domain.models.aggregates.StringIdentifier;
import com.jeferro.shared.locale.domain.models.LocalizedField;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

public abstract class SecondaryAggregateMapper<T extends AggregateRoot<ID>,
	ID extends StringIdentifier,
	DTO> {

  @Named("idToDomain")
  public abstract ID toDomain(String value);

  @Named("idToDTO")
  public String toDTO(ID id) {
	return id.getValue();
  }

  @Mapping(target = "id", qualifiedByName = "idToDomain")
  public abstract T toDomain(DTO dto);

  @Mapping(target = "id", qualifiedByName = "idToDTO")
  public abstract DTO toDTO(T entity);

  protected LocalizedField toDomain(Map<String, String> values) {
	return new LocalizedField(values);
  }

  protected Map<String, String> toDTO(LocalizedField entity) {
	return entity.getValues();
  }
}
