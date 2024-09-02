package com.jeferro.shared.locale.infrastructure.adapters.kafka.mappers;

import java.util.Locale;

import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;

@Mapper
public class LocaleKafkaMapper extends BidirectionalMapper<Locale, String> {

  @Override
  public Locale toDomain(String dto) {
	return Locale.forLanguageTag(dto);
  }

  @Override
  public String toDTO(Locale locale) {
	return locale.toLanguageTag();
  }
}
