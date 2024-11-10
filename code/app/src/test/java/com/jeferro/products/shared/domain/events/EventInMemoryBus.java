package com.jeferro.products.shared.domain.events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.jeferro.shared.ddd.domain.events.Event;
import com.jeferro.shared.ddd.domain.events.EventBus;

public class EventInMemoryBus extends EventBus {

  private final List<Event> events = new ArrayList<>();

  @Override
  protected void send(Event event) {
	events.add(event);
  }

  public int size() {
	return events.size();
  }

  public boolean isEmpty() {
	return events.isEmpty();
  }

  public Event getFirstOrError() {
	return events.getFirst();
  }

  public void forEach(Consumer<Event> action) {
	events.forEach(action);
  }
}