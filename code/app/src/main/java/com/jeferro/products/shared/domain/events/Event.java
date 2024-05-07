package com.jeferro.products.shared.domain.events;

import java.time.Instant;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.entities.Entity;
import org.apache.commons.lang3.StringUtils;

public abstract class Event extends Entity<EventId> {

    private final String occurredBy;

    private final Instant occurredOn;

    protected Event(EventId id, String occurredBy, Instant occurredOn) {
		super(id);

		validateOccurredBy(occurredBy);
		validateOccurredOn(occurredOn);

		this.occurredBy = occurredBy;
        this.occurredOn = occurredOn;
    }

	public String getOccurredBy() {
        return occurredBy;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }

	private static void validateOccurredBy(String occurredBy) {
		if (StringUtils.isBlank(occurredBy)) {
			throw ValueValidationException.createOfMessage("Occurred by is blank");
		}
	}

	private static void validateOccurredOn(Instant occurredOn) {
		if (occurredOn == null) {
			throw ValueValidationException.createOfMessage("Occurred on is null");
		}
	}
}
