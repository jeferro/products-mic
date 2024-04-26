package com.jeferro.products.shared.domain.models.entities;

import com.jeferro.products.shared.domain.events.Event;
import com.jeferro.products.shared.domain.exceptions.ValueValidationException;
import com.jeferro.products.shared.domain.models.identifiers.Identifier;
import com.jeferro.products.shared.domain.models.metadata.Metadata;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    private final Metadata metadata;

    @ToStringExclude
    @EqualsExclude
    private final List<Event> events = new ArrayList<>();

    public AggregateRoot(ID id, Metadata metadata) {
        super(id);

        if(metadata == null){
            throw ValueValidationException.ofMessage("Metadata is null");
        }

        this.metadata = metadata;
    }

    protected void record(Event event) {
        events.add(event);
    }

    public List<Event> pullEvents() {
        List<Event> domainEvents = new ArrayList<>(this.events);

        this.events.clear();

        return domainEvents;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Instant getCreatedAt() {
        return metadata.getCreatedAt();
    }

    public String getCreatedBy() {
        return metadata.getCreatedBy();
    }

    public Instant getUpdatedAt() {
        return metadata.getUpdatedAt();
    }

    public String getUpdatedBy() {
        return metadata.getUpdatedBy();
    }
}
