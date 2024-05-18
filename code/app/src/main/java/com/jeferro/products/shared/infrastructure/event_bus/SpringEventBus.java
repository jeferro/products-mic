package com.jeferro.products.shared.infrastructure.event_bus;

import com.jeferro.products.shared.domain.events.Event;
import com.jeferro.products.shared.domain.events.EventBus;
import com.jeferro.products.shared.domain.events.EventBusPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SpringEventBus extends EventBus {

    private static final Logger logger = LoggerFactory.getLogger(SpringEventBus.class);

    private final Map<Class<Event>, List<EventBusPublisher<?>>> publishers = new HashMap<>();

    public SpringEventBus(ApplicationContext applicationContext) {
        applicationContext.getBeansOfType(EventBusPublisher.class)
                .values()
                .forEach(this::registryPublisher);
    }

    @Override
    protected void publish(Event event) {
        Class<?> eventClass = event.getClass();

        publishEventByEventClass(event, eventClass);
    }

    private void registryPublisher(EventBusPublisher<?> eventBusPublisher) {
        Type type = eventBusPublisher.getClass().getGenericInterfaces()[0];

        if (!(type instanceof ParameterizedType parameterizedType)) {
            throw new IllegalArgumentException("Event superclass is not a parameterized type");
        }

        Class<Event> eventClass = (Class<Event>) parameterizedType.getActualTypeArguments()[0];

        if (publishers.containsKey(eventClass)) {
            publishers.get(eventClass).add(eventBusPublisher);
            return;
        }

        List<EventBusPublisher<?>> publishersByEventClass = new ArrayList<>();
        publishersByEventClass.add(eventBusPublisher);

        publishers.put(eventClass, publishersByEventClass);
    }

    private void publishEventByEventClass(Event event, Class<?> eventClass) {
        if (publishers.containsKey(eventClass)) {
            publishers.get(eventClass)
                    .forEach(publisher -> publishEvent(publisher, event));
        }

        Class<?> superEventClass = eventClass.getSuperclass();

        if(!superEventClass.equals(Object.class)){
            publishEventByEventClass(event, superEventClass);
        }
    }

    private <E extends Event> void publishEvent(EventBusPublisher<?> publisher, E event) {
        try {
            ((EventBusPublisher<E>) publisher).publish(event);
        } catch (Exception cause) {
            logger.error("Error publishing event {} by publisher {}", event,
                    publisher.getClass().getSimpleName(),
                    cause);
        }
    }
}
