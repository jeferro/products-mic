package com.jeferro.shared.domain.events;

public interface EventBusPublisher<E extends Event> {

    void publish(E event) throws InterruptedException;
}
