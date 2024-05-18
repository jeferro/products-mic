package com.jeferro.products.shared.domain.events;

import com.jeferro.products.shared.domain.models.aggregates.AggregateRoot;
import com.jeferro.products.shared.domain.models.aggregates.EntityCollection;
import com.jeferro.products.shared.domain.models.aggregates.Identifier;

public abstract class EventBus {

    public void publishAll(AggregateRoot<?> aggregateRoot) {
        aggregateRoot.pullEvents()
                .forEach(this::publish);
    }

    public <I extends Identifier<?>, E extends AggregateRoot<I>> void publishAll(EntityCollection<I, E> collection) {
        collection.forEach(this::publishAll);
    }

    protected abstract void publish(Event event);
}
