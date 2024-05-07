package com.jeferro.products.shared.domain.models.entities;

import java.util.ArrayList;
import java.util.List;

import com.jeferro.products.shared.domain.events.Event;
import com.jeferro.products.shared.domain.models.auth.Auth;
import com.jeferro.products.shared.domain.models.metadata.Metadata;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

public class AggregateRoot<ID extends Identifier<?>> extends Entity<ID> {

    @ToStringExclude
    @EqualsExclude
    private final List<Event> events = new ArrayList<>();

	protected final Metadata metadata;

    public AggregateRoot(ID id, Metadata metadata) {
        super(id);
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

	protected void markAsModified(Auth auth) {
		metadata.markAsModified(auth);
	}
}
