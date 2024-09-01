package com.jeferro.shared.ddd.domain.events;

public interface EventBusPublisher<E extends Event> {

    void publish(E event) throws Exception;
}
