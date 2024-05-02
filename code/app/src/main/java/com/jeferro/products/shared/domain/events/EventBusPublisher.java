package com.jeferro.products.shared.domain.events;

public interface EventBusPublisher<E extends Event> {

    void publish(E event);
}
