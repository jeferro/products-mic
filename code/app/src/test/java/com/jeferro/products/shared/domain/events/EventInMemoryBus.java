package com.jeferro.products.shared.domain.events;

import java.util.ArrayList;
import java.util.List;

public class EventInMemoryBus extends EventBus {

    private final List<Event> events = new ArrayList<>();

    @Override
    protected void publish(Event event) {
        events.add(event);
    }

    public int size() {
        return events.size();
    }

    public Event getFirstOrError() {
        return events.getFirst();
    }
}