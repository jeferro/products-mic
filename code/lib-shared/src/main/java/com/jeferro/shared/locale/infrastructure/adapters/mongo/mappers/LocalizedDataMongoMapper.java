package com.jeferro.shared.locale.infrastructure.adapters.mongo.mappers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.jeferro.shared.locale.domain.models.LocalizedData;
import com.jeferro.shared.mappers.BidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class LocalizedDataMongoMapper extends BidirectionalMapper<LocalizedData, Map<String, String>> {

  public static final LocalizedDataMongoMapper INSTANCE = Mappers.getMapper(LocalizedDataMongoMapper.class);

  @Override
  public LocalizedData toDomain(Map<String, String> dto) {
	var values = dto.entrySet().stream()
		.map(entry -> {
		  var key = entry.getKey().replace("_", "-");
		  var value = entry.getValue();

		  return Map.entry(key, value);
		})
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

	return new LocalizedData(values);
  }

  @Override
  public Map<String, String> toDTO(LocalizedData localizedData) {
	return localizedData.entrySetStream()
		.map(entry -> {
		  var key = entry.getKey().replace("-", "_");
		  var value = entry.getValue();

		  return Map.entry(key, value);
		})
		.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
  }
}
