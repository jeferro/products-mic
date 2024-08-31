package com.jeferro.shared.domain.events;

import com.jeferro.shared.domain.models.aggregates.Entity;

public abstract class Event extends Entity<EventId> {

  protected Event(EventId id) {
	super(id);
  }
}
