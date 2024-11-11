package com.jeferro.shared.mappers;

import java.util.Map;

import com.jeferro.shared.ddd.domain.events.Event;
import com.jeferro.shared.locale.domain.models.LocalizedField;

public abstract class EventMapper<T extends Event> {

  protected LocalizedField toDomain(Map<String, String> values) {
	return new LocalizedField(values);
  }

  protected Map<String, String> toDTO(LocalizedField entity) {
	return entity.getValues();
  }
}
