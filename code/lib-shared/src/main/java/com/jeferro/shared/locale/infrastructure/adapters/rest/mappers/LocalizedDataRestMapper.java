package com.jeferro.shared.locale.infrastructure.adapters.rest.mappers;

import java.util.Map;

import com.jeferro.shared.locale.domain.models.LocalizedData;
import com.jeferro.shared.auth.infrastructure.ContextManager;
import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class LocalizedDataRestMapper extends BidirectionalMapper<LocalizedData, Map<String, String>> {

  public static final LocalizedDataRestMapper INSTANCE = Mappers.getMapper(LocalizedDataRestMapper.class);

  @Override
  public LocalizedData toDomain(Map<String, String> dto) {
	return new LocalizedData(dto);
  }

  @Override
  public Map<String, String> toDTO(LocalizedData localizedData) {
	var locale = ContextManager.getLocale();

	return localizedData.getValues(locale);
  }
}
