package com.jeferro.shared.ddd.domain.models.aggregates;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;
import lombok.Getter;

@Getter
public abstract class EntityCollection<I extends Identifier, E extends Entity<I>>
	extends ValueObject
	implements Collection<E> {

  private Integer pageSize;

  private Integer pageNumber;

  private Long totalEntities;

  protected final List<E> entities;

  public EntityCollection(List<E> entities) {
	this(entities, null, null, 0L);
  }

  public EntityCollection(List<E> entities, Integer pageSize, Integer pageNumber, long totalPages) {
	this.entities = entities;
	setPageSize(pageSize);
	setPageNumber(pageNumber);
	setTotalEntities(totalPages);
  }

  @Override
  public boolean isEmpty() {
	return entities.isEmpty();
  }

  public boolean isNotEmpty() {
	return !entities.isEmpty();
  }

  @Override
  public int size() {
	return entities.size();
  }

  @Override
  public boolean contains(Object o) {
	return entities.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
	return entities.iterator();
  }

  @Override
  public Object[] toArray() {
	return entities.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
	return entities.toArray(a);
  }

  @Override
  public boolean remove(Object o) {
	return entities.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
	return entities.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
	return entities.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
	return entities.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
	return entities.retainAll(c);
  }

  @Override
  public void clear() {
	entities.clear();
  }

  @Override
  public boolean add(E entity) {
	entities.add(entity);
	return true;
  }

  public E get(int index) {
	return entities.get(index);
  }

  public boolean contains(E entity) {
	return entities.contains(entity);
  }

  public Stream<E> filter(Predicate<? super E> predicate) {
	return entities.stream()
		.filter(predicate);
  }

  public <R> Stream<R> map(Function<E, ? extends R> mapper) {
	return entities.stream()
		.map(mapper);
  }

  public List<I> getIds() {
	return entities.stream()
		.map(Entity::getId)
		.collect(toList());
  }

  public Stream<I> streamIds() {
	return entities.stream()
		.map(Entity::getId);
  }

  public boolean containsId(I id) {
	return findById(id).isEmpty();
  }

  public Optional<E> findById(I id) {
	return entities.stream()
		.filter(entity -> entity.hasSameId(id))
		.findFirst();
  }

  public boolean isPageable() {
	return pageNumber != null
		&& pageSize != null;
  }

  public boolean isNotPageable() {
	return !isPageable();
  }

  public Long getTotalPages() {
	if (isNotPageable()) {
	  return 1L;
	}

	if (totalEntities % pageSize == 0) {
	  return totalEntities / pageSize;
	}

	return totalEntities / pageSize + 1;
  }

  private void setTotalEntities(Long totalEntities) {
	this.totalEntities = totalEntities;
  }

  private void setPageNumber(Integer pageNumber) {
	this.pageNumber = pageNumber;
  }

  private void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
  }

  @Override
  public String toString() {
	return entities.toString();
  }
}
