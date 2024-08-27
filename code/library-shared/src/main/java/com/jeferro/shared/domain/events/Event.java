package com.jeferro.shared.domain.events;

import java.time.Instant;

import com.jeferro.shared.domain.exceptions.internals.ValueValidationException;
import com.jeferro.shared.domain.models.aggregates.Entity;
import org.apache.commons.lang3.StringUtils;

public abstract class Event extends Entity<EventId> {

  private String occurredBy;

  private Instant occurredOn;

  protected Event(EventId id, String occurredBy, Instant occurredOn) {
	super(id);

	setOccurredBy(occurredBy);
	setOccurredOn(occurredOn);
  }

  public String getOccurredBy() {
	return occurredBy;
  }

  public Instant getOccurredOn() {
	return occurredOn;
  }

  private void setOccurredBy(String occurredBy) {
	if (StringUtils.isBlank(occurredBy)) {
	  throw ValueValidationException.createOfMessage("Occurred by is blank");
	}

	this.occurredBy = occurredBy;
  }

  private void setOccurredOn(Instant occurredOn) {
	if (occurredOn == null) {
	  throw ValueValidationException.createOfMessage("Occurred on is null");
	}

	this.occurredOn = occurredOn;
  }
}
