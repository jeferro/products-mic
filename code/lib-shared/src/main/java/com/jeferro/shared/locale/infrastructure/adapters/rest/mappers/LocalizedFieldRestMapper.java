package com.jeferro.shared.locale.infrastructure.adapters.rest.mappers;

import java.util.Map;

import com.jeferro.shared.locale.domain.models.LocalizedField;
import com.jeferro.shared.auth.infrastructure.ContextManager;
import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class LocalizedFieldRestMapper extends BidirectionalMapper<LocalizedField, Map<String, String>> {

  public static final LocalizedFieldRestMapper INSTANCE = Mappers.getMapper(LocalizedFieldRestMapper.class);

  @Override
  public LocalizedField toDomain(Map<String, String> dto) {
	return new LocalizedField(dto);
  }

  @Override
  public Map<String, String> toDTO(LocalizedField localizedField) {
	var locale = ContextManager.getLocale();

	return localizedField.getValues(locale);
  }
}
