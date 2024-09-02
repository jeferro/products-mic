package com.jeferro.shared.ddd.domain.models.value_objects;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class ValueObjectCollection<V extends ValueObject> extends ValueObject {

    protected final List<V> values;

    protected ValueObjectCollection(List<V> values) {
        this.values = values;
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public boolean isNotEmpty() {
        return !values.isEmpty();
    }

    public int size() {
        return values.size();
    }

    public V get(int index) {
        return values.get(index);
    }

    public boolean contains(V value) {
        return values.contains(value);
    }

    public Stream<V> filter(Predicate<? super V> predicate) {
        return values.stream()
                .filter(predicate);
    }

    public <R> Stream<R> map(Function<V, ? extends R> mapper) {
        return values.stream()
                .map(mapper);
    }

    public void forEach(Consumer<V> action) {
        values.forEach(action);
    }
}
