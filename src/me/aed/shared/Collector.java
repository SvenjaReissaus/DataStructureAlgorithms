package me.aed.shared;

public final class Collector<T> {
    private final T[] array;
    private final int size;
    private int next = 0;

    public Collector(final T[] items) {
        array = items;
        size = array.length;
    }

    public Collector<T> add(final T item) {
        if (next >= size) return this;
        array[next++] = item;
        return this;
    }

    public T get(final int index) {
        if (index >= size) return null;
        return array[index];
    }

    public int size() {
        return next;
    }

    public Collector<T> remove(final int index) {
        if (index >= next) return this;
        for (int i = index; i < next; i++) array[i] = array[i + 1];
        next--;
        return this;
    }
}
