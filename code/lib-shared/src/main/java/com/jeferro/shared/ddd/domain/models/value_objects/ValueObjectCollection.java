package com.jeferro.shared.ddd.domain.models.value_objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class ValueObjectCollection<V extends ValueObject>
    extends ValueObject
    implements Collection<V> {

    protected List<V> items;


    protected ValueObjectCollection() {
        this(null);
    }

    protected ValueObjectCollection(List<V> items) {
        setItems(items);
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
    public Iterator<V> iterator() {
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
    public boolean addAll(Collection<? extends V> c) {
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
    public boolean add(V value) {
        items.add(value);
        return true;
    }

    public V get(int index) {
        return items.get(index);
    }

    public boolean contains(V value) {
        return items.contains(value);
    }

    public Stream<V> filter(Predicate<? super V> predicate) {
        return items.stream()
                .filter(predicate);
    }

    public <R> Stream<R> map(Function<V, ? extends R> mapper) {
        return items.stream()
                .map(mapper);
    }

    private void setItems(List<V> items) {
        this.items = items != null
            ? items
            : new ArrayList<>();
    }
}
