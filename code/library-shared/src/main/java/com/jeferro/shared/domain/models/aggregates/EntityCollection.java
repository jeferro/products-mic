package com.jeferro.shared.domain.models.aggregates;

import com.jeferro.shared.domain.models.value_objects.ValueObject;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class EntityCollection<I extends Identifier<?>, E extends Entity<I>> extends ValueObject {

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

    public boolean isEmpty() {
        return entities.isEmpty();
    }

    public boolean isNotEmpty() {
        return !entities.isEmpty();
    }

    public int size() {
        return entities.size();
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

    public void forEach(Consumer<E> action) {
        entities.forEach(action);
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

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Long getTotalEntities() {
        return totalEntities;
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
