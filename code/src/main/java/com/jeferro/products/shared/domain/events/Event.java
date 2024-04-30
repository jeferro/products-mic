package com.jeferro.products.shared.domain.events;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.value_objects.ValueObject;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;

public abstract class Event extends ValueObject {

    private final String occurredBy;

    private final Instant occurredOn;

    protected Event(String occurredBy, Instant occurredOn) {
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
