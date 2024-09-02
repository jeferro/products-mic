package com.jeferro.shared.locale.infrastructure.adapters.kafka.mappers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.jeferro.shared.locale.domain.models.LocalizedField;
import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class LocalizedFieldKafkaMapper extends BidirectionalMapper<LocalizedField, Map<CharSequence, CharSequence>> {

  public static final LocalizedFieldKafkaMapper INSTANCE = Mappers.getMapper(LocalizedFieldKafkaMapper.class);

  @Override
  public LocalizedField toDomain(Map<CharSequence, CharSequence> dto) {
	var values = dto.entrySet().stream()
		.map(entry -> {
		  var key = entry.getKey().toString();
		  var value = entry.getValue().toString();

		  return Map.entry(key, value);
		})
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

	return new LocalizedField(values);
  }

  @Override
  public Map<CharSequence, CharSequence> toDTO(LocalizedField localizedField) {
	return localizedField.entrySetStream()
		.map(entry -> {
		  CharSequence key = entry.getKey();
		  CharSequence value = entry.getKey();

		  return Map.entry(key, value);
		})
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }
}
