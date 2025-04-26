package com.jeferro.shared.ddd.domain.models.projection;

import com.jeferro.shared.ddd.domain.models.aggregates.Identifier;
import com.jeferro.shared.ddd.domain.models.value_objects.ValueObject;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Getter
public abstract class ProjectionCollection<I extends Identifier, E extends Projection<I>>
        extends ValueObject
        implements Collection<E> {

    private Integer pageSize;

    private Integer pageNumber;

    private Long totalItems;

    protected List<E> items;

    public ProjectionCollection() {
        this(null);
    }

    public ProjectionCollection(List<E> items) {
        this(items, null, null, 0L);
    }

    public ProjectionCollection(List<E> items, Integer pageSize, Integer pageNumber, long totalPages) {
        setItems(items);
        setPageSize(pageSize);
        setPageNumber(pageNumber);
        setTotalItems(totalPages);
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean isNotEmpty() {
        return !items.isEmpty();
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean contains(Object o) {
        return items.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }

    @Override
    public Object[] toArray() {
        return items.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return items.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return items.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return items.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return items.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return items.retainAll(c);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public boolean add(E entity) {
        items.add(entity);
        return true;
    }

    public E get(int index) {
        return items.get(index);
    }

    public boolean contains(E entity) {
        return items.contains(entity);
    }

    public Stream<E> filter(Predicate<? super E> predicate) {
        return items.stream()
                .filter(predicate);
    }

    public <R> Stream<R> map(Function<E, ? extends R> mapper) {
        return items.stream()
                .map(mapper);
    }

    public List<I> getIds() {
        return items.stream()
                .map(Projection::getId)
                .collect(toList());
    }

    public Stream<I> streamIds() {
        return items.stream()
                .map(Projection::getId);
    }

    public boolean containsId(I id) {
        return findById(id).isEmpty();
    }

    public Optional<E> findById(I id) {
        return items.stream()
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

        if (totalItems % pageSize == 0) {
            return totalItems / pageSize;
        }

        return totalItems / pageSize + 1;
    }

    private void setItems(List<E> items) {
        this.items = items != null
                ? items
                : new ArrayList<>();
    }

    private void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    private void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    private void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
