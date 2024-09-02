package com.jeferro.shared.locale.infrastructure.adapters.kafka.mappers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.jeferro.shared.locale.domain.models.LocalizedData;
import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class LocalizedDataKafkaMapper extends BidirectionalMapper<LocalizedData, Map<CharSequence, CharSequence>> {

  public static final LocalizedDataKafkaMapper INSTANCE = Mappers.getMapper(LocalizedDataKafkaMapper.class);

  @Override
  public LocalizedData toDomain(Map<CharSequence, CharSequence> dto) {
	var values = dto.entrySet().stream()
		.map(entry -> {
		  var key = entry.getKey().toString();
		  var value = entry.getValue().toString();

		  return Map.entry(key, value);
		})
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

	return new LocalizedData(values);
  }

  @Override
  public Map<CharSequence, CharSequence> toDTO(LocalizedData localizedData) {
	return localizedData.entrySetStream()
		.map(entry -> {
		  CharSequence key = entry.getKey();
		  CharSequence value = entry.getKey();

		  return Map.entry(key, value);
		})
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }
}
