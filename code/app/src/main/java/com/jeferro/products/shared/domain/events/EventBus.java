package com.jeferro.products.shared.domain.events;

import com.jeferro.products.shared.domain.models.entities.AggregateRoot;

public abstract class EventBus {

    public void publishAll(AggregateRoot<?> aggregateRoot) {
        aggregateRoot.pullEvents()
                .forEach(this::publish);
    }

    protected abstract void publish(Event event);
}
