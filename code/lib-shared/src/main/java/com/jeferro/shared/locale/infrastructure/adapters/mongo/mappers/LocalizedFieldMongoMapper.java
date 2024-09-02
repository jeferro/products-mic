package com.jeferro.shared.locale.infrastructure.adapters.mongo.mappers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.jeferro.shared.locale.domain.models.LocalizedField;
import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class LocalizedFieldMongoMapper extends BidirectionalMapper<LocalizedField, Map<String, String>> {

  public static final LocalizedFieldMongoMapper INSTANCE = Mappers.getMapper(LocalizedFieldMongoMapper.class);

  @Override
  public LocalizedField toDomain(Map<String, String> dto) {
	var values = dto.entrySet().stream()
		.map(entry -> {
		  var key = entry.getKey().replace("_", "-");
		  var value = entry.getValue();

		  return Map.entry(key, value);
		})
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

	return new LocalizedField(values);
  }

  @Override
  public Map<String, String> toDTO(LocalizedField localizedField) {
	return localizedField.entrySetStream()
		.map(entry -> {
		  var key = entry.getKey().replace("-", "_");
		  var value = entry.getValue();

		  return Map.entry(key, value);
		})
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }
}
