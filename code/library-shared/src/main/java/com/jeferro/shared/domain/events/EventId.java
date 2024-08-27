package com.jeferro.shared.domain.events;

import java.util.UUID;

import com.jeferro.shared.domain.models.aggregates.Identifier;

public class EventId extends Identifier<String> {

	public EventId(String value) {
		super(value);
	}

	public static EventId create() {
		var value = UUID.randomUUID().toString();

		return new EventId(value);
	}
}
