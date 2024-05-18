package com.jeferro.products.shared.domain.repositories;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.jeferro.products.shared.domain.models.aggregates.AggregateRoot;
import com.jeferro.products.shared.domain.models.aggregates.Identifier;

public abstract class InMemoryRepository<Aggregate extends AggregateRoot<Id>, Id extends Identifier<?>> {

  protected final Map<Id, Aggregate> data = new HashMap<>();

  @SafeVarargs
  public final void init(Aggregate... aggregates) {
	Arrays.stream(aggregates)
		.forEach(aggregate -> data.put(aggregate.getId(), aggregate));
  }

  public void save(Aggregate aggregate) {
	data.put(aggregate.getId(), aggregate);
  }

  public Optional<Aggregate> findById(Id id) {
	if (!data.containsKey(id)) {
	  return Optional.empty();
	}

	var product = data.get(id);

	return Optional.of(product);
  }

  public void deleteById(Id id) {
	data.remove(id);
  }

  public boolean isEmpty() {
	return data.isEmpty();
  }

  public int size() {
	return data.size();
  }

  public boolean contains(Aggregate aggregate) {
	var saved = data.getOrDefault(aggregate.getId(), null);

	return aggregate.equals(saved);
  }
}
