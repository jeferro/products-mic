package com.jeferro.shared.ddd.domain.events;

import com.jeferro.shared.ddd.domain.models.aggregates.Entity;

public abstract class Event extends Entity<EventId> {

  protected Event(EventId id) {
	super(id);
  }
}
