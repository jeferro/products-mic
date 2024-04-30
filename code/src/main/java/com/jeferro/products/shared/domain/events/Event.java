package com.jeferro.products.shared.domain.events;

import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.value_objects.ValueObject;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;

public abstract class Event extends ValueObject {

    private final String occurredBy;

    private final Instant occurredOn;

    protected Event(String occurredBy, Instant occurredOn) {
        if (StringUtils.isBlank(occurredBy)) {
            throw ValueValidationException.ofMessage("Occurred by is blank");
        }

        if (occurredOn == null) {
            throw ValueValidationException.ofMessage("Occurred on is null");
        }

        this.occurredBy = occurredBy;
        this.occurredOn = occurredOn;
    }

    public String getOccurredBy() {
        return occurredBy;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }
}
