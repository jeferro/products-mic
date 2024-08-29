package com.jeferro.shared.domain.events;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeferro.shared.domain.models.aggregates.AggregateRoot;
import com.jeferro.shared.domain.models.aggregates.EntityCollection;
import com.jeferro.shared.domain.models.aggregates.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EventBus {

  private static final Logger logger = LoggerFactory.getLogger(EventBus.class);

  private final Map<Class<Event>, List<EventBusPublisher<?>>> publishers = new HashMap<>();

  public void publishAll(AggregateRoot<?> aggregateRoot) {
	aggregateRoot.pullEvents()
		.forEach(this::publish);
  }

  public <I extends Identifier<?>, E extends AggregateRoot<I>> void publishAll(EntityCollection<I, E> collection) {
	collection.forEach(this::publishAll);
  }

  protected void registryPublisher(EventBusPublisher<?> eventBusPublisher) {
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

  protected void publish(Event event) {
	Class<?> eventClass = event.getClass();

	publishEventByEventClass(event, eventClass);
  }

  private void publishEventByEventClass(Event event, Class<?> eventClass) {
	if (publishers.containsKey(eventClass)) {
	  publishers.get(eventClass)
		  .forEach(publisher -> publishEvent(publisher, event));
	}

	Class<?> superEventClass = eventClass.getSuperclass();

	if (!superEventClass.equals(Object.class)) {
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

	  throw new RuntimeException(cause);
	}
  }
}
